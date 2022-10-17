package com.casey.wj.controller;
/*
 * @author CaseyL
 * @date 2022/9/29 14:53
 * */

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.casey.wj.dto.CommonResponseDto;
import com.casey.wj.entity.Book;
import com.casey.wj.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
//@CrossOrigin
public class BookController {
    private final BookService bookService;

    @GetMapping("api/books")
    @ResponseBody
    public CommonResponseDto getAllBooks() {
        CommonResponseDto responseDto = new CommonResponseDto();
        List<Book> allBooks = bookService.getAllBooks();
        responseDto.setCode(200);
        responseDto.setObject(allBooks);
        return responseDto;
    }

    @PostMapping("api/books")
    @ResponseBody
    public Book addOrUpdate(@RequestBody Book book) {

        bookService.addOrUpdate(book);
        // 为啥不用void
        return book;
    }

    @PostMapping("api/delete")
    @ResponseBody
    public void deleteBook(@RequestBody String json) {
        //  用这个 @DeleteMapping 会不会好些
        JSONObject jsonObject = JSONUtil.parseObj(json);
        String bookId = jsonObject.getStr("bookId");
        bookService.deleteById(Integer.parseInt(bookId));
    }

    @GetMapping("api/categories/{cid}/books")
    @ResponseBody
    public List<Book> findAllBooksByCategory(@PathVariable("cid") int cid){
        if (0!=cid) {
            return bookService.findAllByCategory(cid);
        }
        return bookService.getAllBooks();
    }

    @GetMapping("api/search")
    @ResponseBody
    public List<Book> searchBooks(@RequestParam(value = "keyword") String keyword) {
        if (null == keyword || "".equals(keyword)) {
            return bookService.getAllBooks();
        }
        return bookService.findAllByTitleLikeOrAuthorLike(keyword);
    }

    @PostMapping("api/covers")
    public String coversUpload(MultipartFile file) {
        String folder = "C:\\Users\\CaseyL\\Desktop\\img";
        File imageFolder = new File(folder);
        String filename = file.getOriginalFilename();

        File f = new File(imageFolder, UUID.randomUUID() + filename);

        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}
