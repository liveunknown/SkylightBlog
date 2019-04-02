$(function(){
    editormd("test-editormd", {
        width   : "100%",
        height  : 1000,
        syncScrolling : "single",
        path    : "editormd/lib/",
        //使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
        //saveHTMLToTextarea : true,
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "/uploadImage",
    });
});

function addArticle() {
    var title = $('#title').val();
    console.log("title: " +title);
    var summary = $('#summary').val();
    var categoryId = $('#category').val();
    console.log("categoryId: " +categoryId);
    var content = $('#content').val();
    var labels = $('#labels').val();
    console.log("labels: " +labels);
    var fd = new FormData();
    fd.append('title',title);
    fd.append('summary',summary);
    fd.append('categoryId',categoryId);
    fd.append('content',content);
    fd.append('labels',labels);
    //console.log(fd);
    $.ajax({
        url: "/addArticle",
        type: "POST",
        data: {
            title:title,
            summary:summary,
            categoryId:categoryId,
            content:content,
            labels:JSON.stringify(labels)
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
        }
    });
}

function addArticleByFormData() {
    var title = $('#title').val();
    console.log("title: " +title);
    var summary = $('#summary').val();
    var categoryId = $('#category').val();
    console.log("categoryId: " +categoryId);
    var content = $('#content').val();
    var labels = $('#labels').val();
    console.log("labels: " +labels);
    var fd = new FormData();
    fd.append('title',title);
    fd.append('summary',summary);
    fd.append('categoryId',categoryId);
    fd.append('content',content);
    fd.append('labels',JSON.stringify(labels));
    console.log(fd);
    $.ajax({
        url: "/addArticle",
        type: "POST",
        data:fd,
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(data);
        }
    });
}