/**
 * Created by onion on 2017/6/14.
 */
$(function () {
    var sp = $('[data-id="profile"] a'), scp = $('[data-id="changePassword"] a');
    var sign = config.requestType == 'GET' ? '&' : '?';
    sp.attr('href', sp.attr('href') + sign + 'onlybody=yes').modalTrigger({width: 600, type: 'iframe'});
    scp.attr('href', scp.attr('href') + sign + 'onlybody=yes').modalTrigger({width: 500, type: 'iframe'});

    /* Fixed table actions */
    if ($('table.tablesorter').closest('form').size() > 0) fixedTfootAction($('table.tablesorter').closest('form'));
    /* Fixed table header */
    if ($('table.tablesorter').size() > 0) fixedTheadOfList($('table.tablesorter:first'));

    $('.dropdown-menu .with-search .menu-search').click(function (e) {
        e.stopPropagation();
        return false;
    }).on('keyup change paste', 'input', function () {
        var val = $(this).val().toLowerCase();
        var $options = $(this).closest('.dropdown-menu.with-search').find('.option');
        if (val == '') return $options.removeClass('hide');
        $options.each(function () {
            var $option = $(this);
            $option.toggleClass('hide', $option.text().toString().toLowerCase().indexOf(val) < 0 && $option.data('key').toString().toLowerCase().indexOf(val) < 0);
        });
    });
});
$(function () {
    /* Set the heights of every block to keep them same height. */
    projectBoxHeight = $('#projectbox').height();
    productBoxHeight = $('#productbox').height();
    if (projectBoxHeight < 180) $('#projectbox').css('height', 180);
    if (productBoxHeight < 180) $('#productbox').css('height', 180);

    $('.panel-block').scroll(function () {
        var hasFixed = $(this).find('.fixedHead').size() > 0;
        if (!hasFixed) {
            $(this).css('position', 'relative');
            var hasHeading = $(this).find('.panel-heading').size() > 0;
            var fixed = hasHeading ? $(this).find('.panel-heading').clone() : "<table class='fixedHead' style='position:absolute;top:0px;z-index:10'><thead>" + $(this).find('table thead').html() + '</thead></table>';
            $(this).prepend(fixed);
            if (hasHeading) {
                var firstHeading = $(this).find('.panel-heading:first');
                var lastHeading = $(this).find('.panel-heading:last');
                firstHeading.addClass('fixedHead');
                firstHeading.css({'position': 'absolute', 'top': '0px'});
                firstHeading.width(lastHeading.width());
                firstHeading.height(lastHeading.height());
            }
            else {
                var $fixTable = $(this).find('table.fixedHead');
                $fixTable.addClass($(this).find('table:last').attr('class'));
                var $dataTable = $(this).find('table:last thead th');
                $fixTable.find('thead th').each(function (i) {
                    $fixTable.find('thead th').eq(i).width($dataTable.eq(i).width());
                })
            }
        }
        $(this).find('.fixedHead').css('top', $(this).scrollTop());
    });
});
