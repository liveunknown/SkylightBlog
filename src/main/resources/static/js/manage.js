$(function(){
    LoadPage(1);
});

function LoadPage(page) {
    $.ajax({
        url: "/articleInfoDetailsByCategoryId",
        type: "POST",
        async: false,
        data: {page:page},
        success: function (data) {
            console.log("成功了！");
            $("#tableType").html("文章列表");
            var content = '<thead>'+
                '                    <tr>'+
                '                        <th>ID</th>'+
                '                        <th>分类</th>'+
                '                        <th>文章名称</th>'+
                '                        <th>阅读量</th>'+
                '                        <th>创建时间</th>'+
                '                        <th>操作</th>'+
                '                    </tr>'+
                '                    </thead>'+
                '                    <tbody>';
            for (var i in data) {
                var id = data[i].articleInfoId;
                var createTime = data[i].createBy;
                var category = data[i].category.name;
                var title = data[i].title;
                var views = data[i].views;
                content = content +'<tr>'+
                    '                        <td>'+id+'</td>'+
                    '                        <td>'+category+'</td>'+
                    '                        <td>'+title+'</td>'+
                    '                        <td>'+views+'</td>'+
                    '                        <td>'+createTime+'</td>'+
                    '<td><button class="btn btn-success btn-xs" onclick=""><span class="glyphicon glyphicon-pencil"></span> 编辑</button>&#8194;'+
                    '<button class="btn btn-danger btn-xs" onclick=""><span class="glyphicon glyphicon-trash"></span> 删除</button></td>'+
                    '                    </tr>';
                    '                </div>';
                if(i==data.length)
                {
                    content = content + ' </tbody>';
                }
            }
            $("#table").html(content);
        }, error: function () {
            alert("数据加载错误");
        }
    });
}