import com.google.auto.service.AutoService
import org.redundent.kotlin.xml.Node
import org.redundent.kotlin.xml.PrintOptions
import org.redundent.kotlin.xml.XmlVersion
import org.redundent.kotlin.xml.xml
import java.io.IOException
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.tools.Diagnostic
import javax.tools.StandardLocation


@AutoService(Processor::class)
@SupportedAnnotationTypes("TrackingEventsMap")
class TrackingEventsProcessor : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        val annotatedElements = roundEnv.getElementsAnnotatedWith(TrackingEventsMap::class.java)
        if (annotatedElements.isEmpty()) return false

        val nodes: MutableList<Node> = mutableListOf()
        annotatedElements.take(1).forEach {
            val module = it.getAnnotation(TrackingEventsMap::class.java).module
            processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, it.simpleName)
            it.enclosedElements.filter { it.kind == ElementKind.FIELD && it.getAnnotation(TrackingParam::class.java) != null }
                .forEach {
                    val variableElement = it as VariableElement
                    val node = Node("item")
                    node.text("${variableElement.constantValue}:${it.getAnnotation(TrackingParam::class.java).tracker}")
                    nodes.add(node)
                    processingEnv.messager.printMessage(
                        Diagnostic.Kind.NOTE,
                        variableElement.constantValue.toString()
                    )
                }
            writeToXml(nodes, module)
        }
        return false
    }

    private fun writeToXml(nodes: MutableList<Node>, module: String) {
        val people = xml("resources") {
            version = XmlVersion.V10
            "array" {
                attribute("name", "${module}_tracking_events")
                nodes.forEach {
                    this.addNode(it)
                }
            }
        }

        val asString = people.toString(PrintOptions(singleLineTextElements = true))

        val filer = processingEnv.filer
        try {
            val o = filer.createResource(
                StandardLocation.CLASS_OUTPUT,
                "",
                "tracking_events_map.xml"
            )
            val w = o.openWriter()
            w.write(asString)
            w.flush()
            w.close()
        } catch (e: IOException) {
            processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, e.message)
            throw RuntimeException(e)
        }
    }

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()
}