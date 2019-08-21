/**
 * 文章评论回复模块js
 * Copyright (c) 2019 DragonWen
 */

/*评论*/
function checkCommentForm() {
    if ($(".comment-list .new-comment #commentQQNum").val().trim() == "") {
        layer.msg('QQ不能为空', {icon: 2});
        return false;
    }

    if ($(".comment-list .new-comment #commentName").val().trim() == "") {
        layer.msg('昵称不能为空', {icon: 2});
        return false;
    }
    var content = $(".comment-list .new-comment .input-comment").val().trim();
    if (content == "" || content.length < 2) {
        layer.msg('亲啥都没写喔，或者内容太少啦！', {icon: 5});
        return false;
    }
    return true;
}

$(".comment-list").on("blur", "#commentQQNum", function () {
    var qqNumStr = $('#commentQQNum').val();
    if (qqNumStr == "") {
        return false;
    }
    var data = {qqNum: qqNumStr}
    layer.msg('正在获取QQ信息..', {
        icon: 16
        ,shade: 0.01
    });
    $.ajax({
        type: "GET",
        url: "/f/qqInfo",
        data: data,
        success: function (data, textStatus) {
            if (data.code == 0) {
                layer.close(layer.index);
                $('.comment-list #commentName').val(data.qqInfo.nickname);
                $(".comment-list .avatar #comment-avatar").attr("src", data.qqInfo.avatar);
            } else {
                layer.close(layer.index);
                layer.msg("QQ账号错误..", {icon: 2});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.msg("QQ账号错误..", {icon: 2});
        }
    });
})

$(".comment-list").on("click",".send-comment",function(){
    if (!checkCommentForm()) {
        return;
    }
    var data = {
        type: '2',
        ownerId: $(".blogId").val(),
        content: $(".comment-list .new-comment .input-comment").val(),
        fromQq: $(".comment-list .new-comment #commentQQNum").val(),
        fromName: $(".comment-list .new-comment #commentName").val(),
        fromAvatar: $(".comment-list .new-comment .avatar #comment-avatar")[0].src
    };
    $.ajax({
        type: "POST",
        url: "/f/comments",
        data: data,
        success: function (data) {
            if (data.code == 0) {
                //刷新当前评论框
                layer.msg("评论成功!", {icon: 1});
                $.ajax({
                    type: "Get",
                    data: {blogId: $(".blogId").val()},
                    url: "/f/comments",
                    success: function (data) {
                        $("#comment-body").html(data);
                    }
                })
            } else {
                layer.msg(data.msg, {icon: 2});
            }
        }
    });
});

/*回复*/
function checkReplyForm(id) {
    if ($(".comment-list .new-comment #replyQQNum-" + id).val().trim() == "") {
        layer.msg('QQ不能为空', {icon: 2});
        return false;
    }

    if ($(".comment-list .new-comment #replyName-" + id).val().trim() == "") {
        layer.msg('昵称不能为空', {icon: 2});
        return false;
    }
    var content = $(".comment-list .new-comment #input-reply-" + id).val().trim();
    if (content == "" || content.length < 2) {
        layer.msg('亲啥都没写喔，或者内容太少啦！', {icon: 5});
        return false;
    }
    return true;
}

function getQqInfo(id){
    var qqNumStr = $('#replyQQNum-' + id).val();
    if (qqNumStr == "") {
        return false;
    }
    var data = {qqNum: qqNumStr}
    layer.msg('正在获取QQ信息..', {
        icon: 16
        ,shade: 0.01
    });
    $.ajax({
        type: "GET",
        url: "/f/qqInfo",
        data: data,
        success: function (data, textStatus) {
            if (data.code == 0) {
                layer.close(layer.index);
                $('.comment-list #replyName-' + id).val(data.qqInfo.nickname);
                $(".comment-list .avatar #reply-avatar-" + id).attr("src", data.qqInfo.avatar);
            } else {
                layer.close(layer.index);
                layer.msg("QQ账号错误..", {icon: 2});
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.msg("QQ账号错误..", {icon: 2});
        }
    });
}

function replyComment(id){
    if (!checkReplyForm(id)) {
        return;
    }
    var data = {
        commentId: id,
        fromQq: $(".comment-list .new-comment #replyQQNum-" + id).val(),
        fromName: $(".comment-list .new-comment #replyName-" + id).val(),
        fromAvatar: $(".comment-list .new-comment .avatar #reply-avatar-" + id)[0].src,
        toId: $(".comment-list .new-comment #toReplId-" + id).val(),
        toName: $(".comment-list .new-comment #toReplName-" + id).val(),
        toAvatar: $(".comment-list .new-comment #toReplAvatar-" + id).val(),
        content: $(".comment-list .new-comment #input-reply-" + id).val()
    };
    $.ajax({
        type: "POST",
        url: "/f/reply",
        data: data,
        success: function (data) {
            if (data.code == 0) {
                //刷新当前评论框
                layer.msg("回复成功!", {icon: 1});
                $.ajax({
                    type: "Get",
                    data: {blogId: $(".blogId").val()},
                    url: "/f/comments",
                    success: function (data) {
                        $("#comment-body").html(data);
                    }
                })
            } else {
                layer.msg(data.msg, {icon: 2});
            }
        }
    });
}

/*点赞*/
function commentLike(id, likeNum) {
    var data = {
        id: id,
        likeNum: likeNum + 1
    };
    $.ajax({
        type: "POST",
        url: "/f/commentLike",
        data: data,
        success: function (data) {
            if (data.code == 0) {
                //刷新当前评论框
                layer.msg("点赞成功!", {icon: 1});
                $.ajax({
                    type: "Get",
                    data: {id: id},
                    url: "/f/refreshLike",
                    success: function (data) {
                        $("#like-button-" + id).attr("onclick", "commentLike("+data.commentsInfo.id+","+ data.commentsInfo.likeNum+")");
                        $("#likeNumText-" + id).html(data.commentsInfo.likeNum + '赞');
                    }
                })
            } else {
                layer.msg(data.msg, {icon: 2});
            }
        }
    });
}

/*显示评论操作功能栏*/
function showCommentTool() {
    var commentTool = document.getElementById("comment-tool");
    var commentToolCss = window.getComputedStyle(commentTool, null).getPropertyValue("display");
    if(commentToolCss == 'none'){
        document.getElementById("comment-tool").style.display = "block";
    }
}

/*显示评论操作功能栏*/
function hideCommentTool() {
    document.getElementById("comment-tool").style.display = "none";
}

/*显示回复输入框*/
function showReply(id, toId, toName, toAvatar) {
    var reply = document.getElementById("reply"+id);
    var replyCss = window.getComputedStyle(reply, null).getPropertyValue("display");
    if(replyCss == 'none'){
        document.getElementById("reply"+id).style.display = "block";
        $('#toReplId-' + id).val(toId);
        $('#toReplName-' + id).val(toName);
        $('#toReplAvatar-' + id).val(toAvatar);
    } else {
        document.getElementById("reply"+id).style.display = "none";
    }
}

/*隐藏回复输入框*/
function hideReply(id) {
    document.getElementById("reply"+id).style.display = "none";
}