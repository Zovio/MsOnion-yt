<script type="text/javascript" charset="utf-8" src="/resources/plugins/ueditor/ueditor.config.js?_v=${js$version}"></script>
<script type="text/javascript" charset="utf-8" src="/resources/plugins/ueditor/ueditor.all.min.js?_v=${js$version}"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/resources/plugins/ueditor/lang/zh-cn/zh-cn.js?_v=${js$version}"></script>
<script type="text/javascript">
    if (!app) var app = {};

    // UEditor操作类
    app.ueditorHelper = {
        initEditor: function (options) {
            var editorContainerId = options.editorContainerId;
            var setting = {
                toolbars: [
                    [
                        "source", "|", "undo", "redo", "|", "bold", "italic", "underline", "strikethrough", "|", "superscript", "subscript", "|", "forecolor", "backcolor", "|", "removeformat", "|",
                        "insertorderedlist", "insertunorderedlist", "|", "selectall", "cleardoc", "rowspacingtop", "rowspacingbottom", "lineheight", "|", "paragraph", "|", "fontfamily", "fontsize",
                        "|", "justifyleft", "justifycenter", "justifyright", "justifyjustify", "|",
                        "link", "unlink", "horizontal", "fullscreen"
                    ]
                ],
                initialFrameWidth: 700,
                initialFrameHeight: 200,
                zIndex: 500
            };
            setting = $.extend({}, setting, options);
            return UE.getEditor(editorContainerId, setting);
        },
        initImageEditor: function (options) {
            var editorContainerId = options.editorContainerId;
            var setting = {
                toolbars: [
                    [
                        "source", "|", "undo", "redo", "|", "bold", "italic", "underline", "strikethrough", "|", "superscript", "subscript", "|", "forecolor", "backcolor", "|", "removeformat", "|",
                        "insertorderedlist", "insertunorderedlist", "|", "selectall", "cleardoc", "rowspacingtop", "rowspacingbottom", "lineheight", "|", "paragraph", "|", "fontfamily", "fontsize",
                        "|", "justifyleft", "justifycenter", "justifyright", "justifyjustify", "|",
                        "link", "unlink", "horizontal", "fullscreen", "|",
                        'simpleupload', //单图上传
                        'insertimage', //多图上传
                        // 'scrawl', //涂鸦
                        // 'snapscreen', //截图
                        'insertvideo' //视频
                        // 'attachment', //附件
                    ]
                ],
                catchRemoteImageEnable: true,//设置是否抓取远程图片
                initialFrameWidth: 700,
                initialFrameHeight: 200,
                zIndex: 500
            };
            setting = $.extend({}, setting, options);
            return UE.getEditor(editorContainerId, setting);
        }
    };
</script>