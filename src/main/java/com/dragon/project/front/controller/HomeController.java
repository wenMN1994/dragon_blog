package com.dragon.project.front.controller;

import com.dragon.common.constant.CommonConstant;
import com.dragon.common.utils.QQUtil;
import com.dragon.framework.aspectj.lang.annotation.VLog;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.project.blog.blog.domain.Blog;
import com.dragon.project.blog.blog.service.BlogService;
import com.dragon.project.blog.category.service.CategoryService;
import com.dragon.project.blog.comments.domain.CommentsInfo;
import com.dragon.project.blog.comments.domain.CommentsReply;
import com.dragon.project.blog.comments.service.CommentsInfoService;
import com.dragon.project.blog.comments.service.CommentsReplyService;
import com.dragon.project.blog.tag.domain.Tag;
import com.dragon.project.blog.tag.service.TagService;
import com.dragon.project.front.service.HomeService;
import com.dragon.project.link.service.LinkService;
import com.dragon.project.system.carouselMap.service.CarouselMapService;
import com.dragon.project.system.notice.service.INoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 前台首页Controller
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@RequestMapping({"/","/my_blog"})
public class HomeController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private CarouselMapService carouselMapService;

    @Autowired
    private CommentsInfoService commentsInfoService;

    @Autowired
    private CommentsReplyService commentsReplyService;

    /**
     * 博客首页
     * @param pageNum
     * @param model
     * @return
     */
    @VLog(title = "首页")
    @RequestMapping("")
    public String index(Integer pageNum, Model model){
        setCommonMessage(model);
        Blog blog = new Blog();
        blog.setModule("1");
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 12, "create_time desc");
        model.addAttribute("blogs", new PageInfo<>(homeService.selectFrontBlogList(blog)));
        //最新课程
        model.addAttribute("repositoryList", blogService.selectNewestUpdateRepository());
        //最新源码
        model.addAttribute("sourceCodeList", blogService.selectNewestUpdateSourceCode());
        //放置轮播图
        model.addAttribute("carouselMaps", carouselMapService.selectCarouselMapListFront());
        return "front/index/index";
    }

    private void setCommonMessage(Model model) {
        //获取分类下拉项中的分类
        model.addAttribute("categories", categoryService.selectSupportCategoryList());
        //查询通知
        model.addAttribute("notices", noticeService.selectNoticeListDisplay());
        //查询所有的标签
        model.addAttribute("tags", tagService.selectTagList(new Tag()));
        //查询文章排行
        model.addAttribute("blogRanking", blogService.selectBlogRanking());
        //查询推荐博文
        model.addAttribute("supportBlog", blogService.selectSupportBlog());
        //查询你喜欢的文章
        model.addAttribute("randBlogList", blogService.selectRandBlogList());
        //查询文章总数
        model.addAttribute("articleCounts",blogService.selectBlogCountByStatus(1));
        //查询评论总数
        model.addAttribute("commentCounts", commentsInfoService.commentsInfoCount());
        //获取友链信息
        model.addAttribute("links", linkService.selectLinkListFront());
    }

    /**
     * 轮播图点击量
     * @param carouselId
     * @param url
     * @return
     */
    @GetMapping("/f/carouselMap/{carouselId}")
    public String carouselMapI(@PathVariable Integer carouselId, String url) {
        //增加点击量
        carouselMapService.incrementCarouselClickById(carouselId);
        return redirect(url);
    }

    /**
     * 个人博客日记
     * @param pageNum
     * @param model
     * @return
     */
    @VLog(title = "个人博客")
    @RequestMapping("blog")
    public String blog(Integer pageNum, Model model){
        setCommonMessage(model);
        Blog blog = new Blog();
        blog.setModule("1");
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 15, "create_time desc");
        model.addAttribute("blogs", new PageInfo<>(homeService.selectFrontBlogList(blog)));
        return "front/blog/blog";
    }

    /**
     * 博客
     * @param blogId
     * @param model
     * @return
     */
    @VLog(title = "博客")
    @GetMapping("/f/article/{blogId}.html")
    public String article(@PathVariable Integer blogId, Model model) {
        setCommonMessage(model);
        Blog blog = blogService.selectBlogWithTextAndTagsAndCategoryByBlogId(blogId);
        //只能访问是已经发表的文章
        if (!CommonConstant.one.equals(blog.getStatus())) {
            return "error/404";
        }
        model.addAttribute("blog", blog);
        model.addAttribute("nextBlog", blogService.selectNextBlogById(blogId));
        model.addAttribute("previousBlog", blogService.selectPreviousBlogById(blogId));
        model.addAttribute("randBlogList", blogService.selectRandBlogList());
        model.addAttribute("commentCounts",commentsInfoService.commentsInfoCountByOwnerId(blogId));
        model.addAttribute("commentsInfoList", commentsInfoService.selectCommentsInfoByOwnerId(blogId));
        return "front/article/article";
    }

    /**
     * 随笔分类
     * @param categoryId
     * @param pageNum
     * @param model
     * @return
     */
    @VLog(title = "随笔分类")
    @GetMapping({"/f/category/{categoryId}.html"})
    public String category(@PathVariable Integer categoryId, Integer pageNum, Model model) {
        setCommonMessage(model);
        model.addAttribute("category", categoryService.selectCategoryById(categoryId));
        Blog blog = new Blog();
        blog.setCategoryId(categoryId);
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 15, "create_time desc");
        model.addAttribute("blogs", new PageInfo<>(homeService.selectFrontBlogList(blog)));
        return "front/category/category";
    }

    /**
     * 归档
     */
    @VLog(title = "归档")
    @GetMapping("archives")
    public String archives(Model model) {
        setCommonMessage(model);
        model.addAttribute("archives", homeService.selectArchives());
        return "front/archives/archives";
    }

    /**
     * 源码库
     * @param pageNum
     * @param model
     * @return
     */
    @VLog(title = "源码库")
    @RequestMapping("sourcecode")
    public String sourcecode(Integer pageNum, Model model){
        setCommonMessage(model);
        Blog blog = new Blog();
        blog.setModule("3");
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 15, "create_time desc");
        model.addAttribute("blogs", new PageInfo<>(homeService.selectFrontBlogList(blog)));
        return "front/sourcecode/sourcecode";
    }

    /**
     * 知识库
     * @param pageNum
     * @param model
     * @return
     */
    @VLog(title = "知识库")
    @RequestMapping("repository")
    public String repository(Integer pageNum, Model model){
        setCommonMessage(model);
        Blog blog = new Blog();
        blog.setModule("2");
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 15, "create_time desc");
        model.addAttribute("blogs", new PageInfo<>(homeService.selectFrontBlogList(blog)));
        return "front/repository/repository";
    }

    /**
     * 关于我
     * @return
     */
    @VLog(title = "关于我")
    @RequestMapping("about")
    public String about(Model model){
        setCommonMessage(model);
        return "front/about/about";
    }

    /**
     * 标签
     * @param tagId
     * @param pageNum
     * @param model
     * @return
     */
    @VLog(title = "标签")
    @GetMapping("/f/tag/{tagId}.html")
    public String tag(@PathVariable Integer tagId, Integer pageNum, Model model) {
        setCommonMessage(model);
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 10, "b.create_time desc");
        List<Blog> blogs = blogService.selectBlogListByTagId(tagId);
        model.addAttribute("blogs", new PageInfo(blogs));
        model.addAttribute("tag", tagService.selectTagById(tagId));
        return "front/tag/tag";
    }

    /**
     * 友链跳转
     */
    @VLog(title = "友链跳转")
    @GetMapping("/f/linkRedirect")
    public String redirectTo(String ref, Integer id) {
        //增加点击量
        linkService.incrementLinkClickById(id);
        return redirect(ref);
    }

    /**
     * 搜索
     * @param keyWord
     * @param pageNum
     * @param model
     * @return
     */
    @VLog(title = "搜索")
    @GetMapping("/f/search/{keyWord}.html")
    public String search(@PathVariable String keyWord, Integer pageNum, Model model) {
        setCommonMessage(model);
        PageHelper.startPage(pageNum == null ? 1 : pageNum, 10, "create_time desc");
        Blog blog = new Blog();
        blog.setTitle(keyWord);
        List<Blog> blogs = blogService.selectBlogList(blog);
        model.addAttribute("blogs", new PageInfo<>(blogs));
        model.addAttribute("keyWord", keyWord);
        return "front/search/search";
    }

    /**
     * 添加评论
     */
    @PostMapping("/f/comments")
    @ResponseBody
    public AjaxResult comments(CommentsInfo commentsInfo) {
        commentsInfo.setFromId(UUID.randomUUID().toString().replaceAll("-", ""));
        int i = commentsInfoService.insertCommentInfo(commentsInfo);
        if (i==1){
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    /**
     * 添加评论
     */
    @PostMapping("/f/reply")
    @ResponseBody
    public AjaxResult reply(CommentsReply commentsReply) {
        commentsReply.setFromId(UUID.randomUUID().toString().replaceAll("-", ""));
        int i = commentsReplyService.insertCommentReply(commentsReply);
        if (i==1){
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    /**
     * 刷新当前评论框
     */
    @GetMapping("/f/comments")
    public String commentSync(Integer blogId, Model model) {
        logger.info("返回信息："+blogId);
        model.addAttribute("commentsInfoList", commentsInfoService.selectCommentsInfoByOwnerId(blogId));
        return "front/common/common :: comment";
    }

    /**
     * 刷新当前点赞数
     */
    @PostMapping("/f/commentLike")
    @ResponseBody
    public AjaxResult commentLike(CommentsInfo commentsInfo) {
        int isSuccess = commentsInfoService.updateCommentsInfo(commentsInfo);
        if(isSuccess == 1){
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    /**
     * 刷新当前点赞数
     */
    @GetMapping("/f/refreshLike")
    @ResponseBody
    public AjaxResult refreshLike(Integer id) {
        return AjaxResult.success().put("commentsInfo",commentsInfoService.selectCommentsInfoById(id));
    }

    /**
     * 根据QQ账号获取用户信息
     * @param qqNum
     * @return
     */
    @GetMapping("/f/qqInfo")
    @ResponseBody
    public AjaxResult qqInfo(Long qqNum) {
        QQUtil.QQInfo qqByQQNum = QQUtil.getQQByQQNum(qqNum);
        if (Objects.nonNull(qqByQQNum)) {
            return AjaxResult.success().put("qqInfo", qqByQQNum);
        }
        return AjaxResult.error();
    }
}
