/**
 * 复制移动选择目标文件文件夹并ajax加载选择文件加下的文件夹
 */
$("#thismodal .box-body").on("click",".openpath",function(){
	if($(this).hasClass("modalajax")){
		var $ul = $(this).parents(".box-header").next();
		$(this).parents(".box-header").find(".jiajian").addClass("glyphicon-minus").removeClass("glyphicon-plus");
		$ul.css("display","block");
	}else{
		console.log("box-header");
	}
});

$(".filetypedocument").click(function(){
	$(".loadfiletype").load("filetypeload",{type:"document"});
});
$(".filetypeimage").click(function(){
	$(".loadfiletype").load("filetypeload",{type:"picture"});
});
$(".filetypemusic").click(function(){
	$(".loadfiletype").load("filetypeload",{type:"music"});
});
$(".filetypevedio").click(function(){
	$(".loadfiletype").load("filetypeload",{type:"video"});
});
$(".filetypeyasuo").click(function(){
	$(".loadfiletype").load("filetypeload",{type:"yasuo"});
});
$(".filetypetrash").click(function(){
	$(".loadfiletype").load("filetypeload",{type:"trash"});
});
$(".filetypeshare").click(function(){
	$(".loadfiletype").load("filetypeload",{type:"share"});
});

/**
 * 搜索js
 */
$(".loadfiletype").on("click",".findfileandpathgo",function(){
	$.modal.alertWarning("功能开发中...");
	// var findFileAndPath = $(".loadfiletype .box-header .findfileandpath").val();
	// var loadType = $(".loadfiletype .box-header .loadfilestype").val();
	// alert(findFileAndPath+loadType);
	// $(".loadfiletype").load("findfileandpath",{type:loadType,findfileandpath:findFileAndPath});
});

/**
 * 删除load js
 */
$(".loadfiletype").on("click",".loaddelete",function(){
		var checkpathids = new Array();
		var checkfileids = new Array();
		checkedpaths2(checkpathids,checkfileids);
		
		var loadtype = $(".loadfiletype .box-header .loadfilestype").val();
		
		console.log(checkpathids);
		console.log(checkfileids);
		console.log(loadtype);
		
		$(".loadfiletype").load("fileloaddeletefile",{type:loadtype,'checkpathids[]':checkpathids,'checkfileids[]':checkfileids});
	
});
$(".loadfiletype").on("click",".loadokshare",function(){
	var checkpathids = new Array();
	var checkfileids = new Array();
	checkedpaths2(checkpathids,checkfileids);
	var loadtype = $(".loadfiletype .box-header .loadfilestype").val();
	
	$(".loadfiletype").load("fileloadshare",{type:loadtype,'checkfileids[]':checkfileids});
	
});
/**
 * 回收战load js
 */
$(".loadfiletype").on("click",".loadtrash",function(){
	var checkPathIds = new Array();
	var checkFileIds = new Array();
	checkedpaths2(checkPathIds,checkFileIds);
	
	var loadtype = $(".loadfiletype .box-header .loadfilestype").val();
	
	$(".loadfiletype").load("fileloadtrashfile",{type:loadtype,'checkPathIds[]':checkPathIds,'checkFileIds[]':checkFileIds});
});

$(".loadfiletype").on("click",".filereturnback",function(){
	var checkpathids = new Array();
	var checkfileids = new Array();
	checkedpaths2(checkpathids,checkfileids);
	console.log("filereturnback");
	var loadtype = $(".loadfiletype .box-header .loadfilestype").val();
	
	$(".loadfiletype").load("filereturnback",{type:loadtype,'checkpathids[]':checkpathids,'checkfileids[]':checkfileids});
	
});


/**
 * 重命名load js
 */
$(".loadfiletype").on("click",".okfilerename",function(){
	var checkedfile = $(this).parents(".file-one.file-one-check");
	var loadtype = $(".loadfiletype .box-header .loadfilestype").val();
	
	var renamefp = checkedfile.find(".renamefp").val();
	var creatpathinput = checkedfile.find(".creatpathinput").val();
	var isfile = checkedfile.find(".isfile").val();
	var pathId = checkedfile.find(".pathId").val();
	
	console.log(renamefp);
	console.log(creatpathinput);
	console.log(isfile);
	console.log(pathId);
	
	$(".loadfiletype").load("fileloadrename",{type:loadtype,renamefp:renamefp,creatpathinput:creatpathinput,isfile:isfile,pathId:pathId})
});


/**
 * 得到选择的 文件和文件夹
 * @param pathids
 * @param fileids
 * @returns
 */
function checkedpaths2(pathids,fileids){
	var checkedpaths =$(".file-one.file-one-check");
	var i = 0;
	var j = 0;
	checkedpaths.each(function(){
		if($(this).find(".file-img").hasClass("path")){
			pathids[i] = $(this).find(".pathmessage").val();
			i += 1;
		}else{
			if(!$(this).hasClass("diplaynone")){
				fileids[j] = $(this).find(".filemessage").val();
				console.log($(this).find(".filemessage").val());
				j += 1;
			}
		}
	});
}