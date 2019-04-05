$(function(){
    LoadPage(1);
});

function LoadPage(page) {
    $.ajax({
        url: "/categories",
        type: "GET",
        async: false,
        data: {},
        success: function (data) {
            console.log("成功了！");
            $("#tableType").html("分类列表");
            var content = '<thead>'+
                '                    <tr>'+
                '                        <th>ID</th>'+
                '                        <th>名称</th>'+
                '                        <th>文章数量</th>'+
                '                        <th>创建时间</th>'+
                '                        <th>操作</th>'+
                '                    </tr>'+
                '                    </thead>'+
                '                    <tbody>';
            var id ;
            var name ;
            var number ;
            var createTime ;

            for(var i in data)
            {
                id = data[i].id;
                name = data[i].name;
                number = data[i].number;
                createTime = data[i].createBy;
                content = content +'<tr>'+
                    '                        <td>'+id+'</td>'+
                    '                        <td>'+name+'</td>'+
                    '                        <td>'+number+'</td>'+
                    '                        <td>'+createTime+'</td>'+
                    '<td><button class="btn btn-success btn-xs" onclick="loadModifyModal('+id+')" ><span class="glyphicon glyphicon-pencil"></span> 编辑</button>&#8194;'+
                    '<button class="btn btn-danger btn-xs" onclick="deleteCategory('+id+')" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-trash"></span> 删除</button></td>'+
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

function addCategory() {
        var category = $('#category').val();
        console.log("category: " +category);
        $.ajax({
            url: "/addCategory",
            type: "POST",
            data: {
               name:category
            },
            dataType: "json",
            success: function (data) {
                console.log(data);
                alert("添加成功~");
                document.location.reload();
            }, error: function () {
                alert("数据加载错误");
            }
        });

}

function deleteCategory(id) {
    $.ajax({
        type: "GET",
        url: "/deleteCategory",
        data: {id:id},
        success: function (data) {
            console.log(data);
            alert("删除分类成功");
            document.location.reload();
        }, error: function () {
            alert("数据加载错误");
        }
    });
}

function modifyCategory(id) {
    var category = $('#modifyCategory').val();
    $.ajax({
        type: "POST",
        url: "/updateCategory",
        async: false,
        data: {
            id:id,
            name:category
        },
        success: function (data) {
            console.log(data);
            alert("更新分类成功~")
            Reload();
        }, error: function () {
            alert("数据加载错误");
        }
    });
}

function loadModal() {
    $('#myModal').modal('show');
    var Buttons = '<button type="button" class="btn btn-primary" onclick="addCategory()">确定</button>'+
                  '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>';
    $("#modalFooter").html(Buttons);
}

function Reload() {
    document.location.reload();
}

function loadModifyModal(id) {
    $('#modifyModal').modal('show');
    $('#history').val(id);
    var Buttons = '<button type="button" class="btn btn-primary" onclick="modifyCategory('+id+')">确定</button>'+
        '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>';
    $("#modifyModalFooter").html(Buttons);
}