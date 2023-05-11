package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Categorianews;
import com.example.demo.entities.News;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.NewsRepository;

@Controller 
@RequestMapping("/newsview")
public class NewsViewController {

	@Autowired
	NewsRepository newsRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<News> news = newsRepository.findAll();
		model.addAttribute("news", news);
		return "index.html";
	}
	
	@GetMapping("/new")
    public String newNews(Model model) {
		News noticia = new News();
	    model.addAttribute("news", noticia);
	    List<Categorianews> categorias = categoriaRepository.findAll();
	    model.addAttribute("categorias", categorias); // agregamos la lista de categorías al modelo
	    return "newsForm";
    }

    @PostMapping("")
    public String create(@ModelAttribute("news") News noticia, BindingResult result) {
        /*if (result.hasErrors()) {
            return "newsForm";
        }*/
        newsRepository.save(noticia);
        return "redirect:/newsview/listar";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
    	Optional<News> noticia = newsRepository.findById(id);
        model.addAttribute("news", noticia);
        List<Categorianews> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        return "newsForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("news") News noticia, BindingResult result) {
        if (result.hasErrors()) {
            return "newsForm";
        }
        newsRepository.save(noticia);
        return "redirect:/newsview/listar";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
    	newsRepository.deleteById(id);
        return "redirect:/newsview/listar";
    }
	
}
