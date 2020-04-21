@TrackingEventsMap("incentive")
object TrackingEvents {
    private const val MIXPANEL_TRACKER = "MIXPANEL_TRACKER"

    @TrackingParam(tracker = MIXPANEL_TRACKER)
    const val DRIVER_MENU_CLICK_QUEST = "DriverMenu_Click_QuestSection"

    @TrackingParam(tracker = MIXPANEL_TRACKER)
    const val DRIVER_MENU_CLICK_QUEST2 = "DriverMenu_Click_QuestSection2"

    @TrackingParam(tracker = MIXPANEL_TRACKER)
    const val DRIVER_MENU_CLICK_QUEST3 = "DriverMenu_Click_QuestSection3"
}