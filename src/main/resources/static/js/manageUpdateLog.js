$(function(){
    LoadAllUpdateLog();
});

function LoadAllUpdateLog() {
    $.ajax({
        url: "/getUpdateLogList",
        type: "GET",
        async: false,
        success: function (data) {
            console.log("成功了！");
            $("#tableType").html("更新日志列表");
            var content = '<thead>'+
                '                    <tr>'+
                '                        <th>ID</th>'+
                '                        <th>日期</th>'+
                '                        <th>日志内容</th>'+
                '                    </tr>'+
                '                    </thead>'+
                '                    <tbody>';
            var id ;
            var updateDate ;
            var updateContent ;

            for(var i in data)
            {
                id = data[i].id;
                updateDate = data[i].updateDate;
                updateContent = data[i].updateContent;

                content = content +'<tr>'+
                    '                        <td>'+id+'</td>'+
                    '                        <td>'+updateDate+'</td>'+
                    '                        <td>'+updateContent+'</td>'+
                    '<td><button class="btn btn-success btn-xs" onclick="loadModifyModal('+id+')" ><span class="glyphicon glyphicon-pencil"></span> 编辑</button>&#8194;'+
                    '<button class="btn btn-danger btn-xs" onclick="loadDeleteModal('+id+')" data-toggle="modal" data-target="#deleteModal"><span class="glyphicon glyphicon-trash"></span> 删除</button></td>'+
                    '                    </tr>';
                '                </div>';
                if(i==data.length)
                {
                    content = content + ' </tbody>';
                }
            }
            $("#table").html(content);
        }, error: function () {
            alert("更新日志加载错误");
        }
    });
}

function addUpdateLog() {
        var updateDate = $('#updateDate').val();
        var updateContent = $('#updateContent').val();

        $.ajax({
            url: "/addUpdateLog",
            type: "POST",
            data: {
                updateDate:updateDate,
                updateContent:updateContent
            },
            dataType: "json",
            success: function (data) {
                console.log(data);
                if(data == true) {
                    alert("添加更新日志成功~");
                    document.location.reload();
                }
                else{
                    alert(data.msg);
                    console.log("返回的信息是："+data.msg);
                }
            }, error: function () {
                alert("添加更新日志错误");
            }
        });

}

function deleteUpdateLog(id) {
    $.ajax({
        type: "GET",
        url: "/deleteUpdateLog",
        data: {id:id},
        success: function (data) {
            console.log(data);
            if(data == true) {
                alert("删除更新日志成功~");
                document.location.reload();
            }
            else{
                alert(data.msg);
                console.log("返回的信息是："+data.msg);
            }
        }, error: function () {
            alert("删除更新日志失败！");
        }
    });
}

function modifyUpdateLog(id) {
    var updateDate = $('#modifiedDate').val();
    var updateContent = $('#modifiedContent').val();
    $.ajax({
        type: "POST",
        url: "/updateUpdateLog",
        async: false,
        data: {
            id:id,
            updateDate:updateDate,
            updateContent:updateContent
        },
        success: function (data) {
            console.log(data);
            if(data==true)
            {
                alert("更新更新日志成功~");
                Reload();
            }
            else{
                alert(data.msg);
                console.log("返回的信息是："+data.msg);
            }
        }, error: function () {
            alert("更新更新日志失败");
        }
    });
}

function loadModal() {
    $('#myModal').modal('show');
    var Buttons = '<button type="button" class="btn btn-primary" onclick="addUpdateLog()">确定</button>'+
                  '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>';
    $("#modalFooter").html(Buttons);
}

function Reload() {
    document.location.reload();
}

function loadModifyModal(id) {
    $('#modifyModal').modal('show');
    $('#history').val(id);
    var Buttons = '<button type="button" class="btn btn-primary" onclick="modifyUpdateLog('+id+')">确定</button>'+
        '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>';
    $("#modifyModalFooter").html(Buttons);
}

function loadDeleteModal(id) {
    var Buttons =  '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'+
        '<button type="button" class="btn btn-primary" onclick="deleteUpdateLog('+id+')">删除</button>';
    $("#deleteModalFooter").html(Buttons);
}
