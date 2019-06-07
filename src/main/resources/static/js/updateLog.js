$(function(){
    LoadUpdateLogs();
});

function LoadUpdateLogs() {
    $.ajax({
        url: "/getUpdateLogList",
        type: "Get",
        async: false,
        success: function (data) {
            var content = "";
            var updateDate;
            var updateContent;
            var updateLogArray = new Array();
            var updateLogs;

            for(var i in data)
            {
                updateDate = data[i].updateDate;
                updateContent = data[i].updateContent;
                updateLogArray = updateContent.split(" ");
                updateLogs = " ";

                for(var j=0; j<updateLogArray.length; j++)
                {
                  updateLogs = updateLogs + '<p>'+ updateLogArray[j] +'</p>';
                }

                content = content + '<div class="update-log " data-am-scrollspy="{animation: \'slide-left\'}" style="opacity: 100;">'+
                    '                        <div class="update-log-date">'+
                    '                            <i class="am-icon-hashtag"></i>'+
                    '                            <h2>'+ updateDate +'</h2>'+
                    '                        </div>'+
                    '                        <div class="update-log-content">'+
                    '                            <blockquote>' + updateLogs + '</blockquote>'+
                    '                        </div>'+
                    '                    </div>'
                ;
            }

            $("#updateLogs").append(content);

        }, error: function () {
            alert("请求更新日志时出现错误！");
        }

    });
}