package com.gamotrance.OTT.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.services.SingleVideoServicesImplimentation;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class VideoWebController {
	 private final SingleVideoServicesImplimentation salesRepository;
	
	 public VideoWebController(SingleVideoServicesImplimentation salesRepository) {
		super();
		this.salesRepository = salesRepository;
	}

	private String add_edit_template="admin/product/add-edit-product";
	    private String list_template="admin/product/list-product";
	    private String list_redirect="redirect:/product/list";


	    @GetMapping("/add")
	    public String addSingleVideo(SingleVideo product, Model model){
	        model.addAttribute("product", product);
	        List<SingleVideo> productTypes = salesRepository.getAllSingleVideo();
	        model.addAttribute("productTypes",productTypes);

	        return add_edit_template;
	    }


	    @GetMapping("/edit/{id}")
	    public String editProduct(@PathVariable("id") int id, Model model){
	    	SingleVideo product = salesRepository.getVideoById(id);
	        model.addAttribute("product", product);

	        List<SingleVideo> productTypes = salesRepository.getAllSingleVideo();
	        model.addAttribute("productTypes",productTypes);

	        return add_edit_template;
	    }

	    @PostMapping("/save")
	    public String saveProduct(@Valid @ModelAttribute("product") SingleVideo product, BindingResult result, Model model){
	        model.addAttribute("product", product);
	        List<SingleVideo> productTypes = salesRepository.getAllSingleVideo();
	        model.addAttribute("productTypes",productTypes);

	        if(result.hasErrors()){
	            return add_edit_template;
	        }

	        //productService.save(product);
	        salesRepository.addSingleVideo(product);
	        return list_redirect+"?success";
	    }



	    @GetMapping("/delete/{id}")
	    public String deleteProduct(@PathVariable("id") int id, Model model) {
	    	SingleVideo product = salesRepository.getVideoById(id);

	        salesRepository.deleteSingleVideo(product);
	        return list_redirect+"?deleted";
	    }

	    @GetMapping("/list")
	    public String listProduct(Model model) {
//	        List<ProductType> productTypes = productTypeService.listAll();
//	        model.addAttribute("productTypes",productTypes);

	        List<SingleVideo> listProducts = salesRepository.getAllSingleVideo();
	       // List<Product> listProducts = productService.listAll();
	        model.addAttribute("listProducts", listProducts);

	        return list_template;
	    }
}
