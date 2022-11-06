$(function (){
    var $calendar = {
        language: 'zh-CN',
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        minView: 'month',
    };
    $("#startDate").datetimepicker($calendar);
    $("#endDate").datetimepicker($calendar);
    $("#createDate").datetimepicker($calendar);
})