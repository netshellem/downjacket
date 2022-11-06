$(function (){
    const DAY = 1000 * 60 * 60 * 24;
    var startDate = new Date() - 30 * DAY;
    var $calendar = {
        language: 'zh-CN',
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        minView: 'month',
        startDate: new Date(startDate)
    };
    $("#startDate").datetimepicker($calendar);
    $("#endDate").datetimepicker($calendar);
    $("#createDate").datetimepicker($calendar);
})