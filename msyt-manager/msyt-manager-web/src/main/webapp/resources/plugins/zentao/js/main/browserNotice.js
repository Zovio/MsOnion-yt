/**
 * Created by onion on 2017/6/14.
 */
browserNotice = '你目前使用的浏览器可能无法得到最佳浏览效果，建议使用Chrome、火狐、IE9+、Opera、Safari浏览器。'
function ajaxIgnoreBrowser() {
    $.get(createLink('misc', 'ajaxIgnoreBrowser'));
}
$(function () {
    showBrowserNotice()
});
