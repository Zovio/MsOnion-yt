/**
 * Created by onion on 2017/6/14.
 */
function sortTable(selector, options) {
    var $table = $(selector);
    $table.tablesorter($.extend(
        {
            saveSort: true,
            widgets: ['zebra', 'saveSort'],
            widgetZebra: {css: ['odd', 'even']}
        }, $table.data(), options)).on('mouseenter', 'tbody tr', function () {
        $(this).addClass('hoover');
    }).on('mouseleave', 'tbody tr', function () {
        $(this).removeClass('hoover');
    }).on('click', 'tbody tr', function () {
        $(this).toggleClass('clicked');
    });
}
$.fn.sortTable = function (options) {
    return this.each(function () {
        sortTable(this, options);
    });
};
/* sort table after page load. */
$(function () {
    $('.tablesorter').sortTable();
});