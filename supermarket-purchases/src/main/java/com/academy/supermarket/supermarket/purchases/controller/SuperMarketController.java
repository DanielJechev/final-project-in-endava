package com.academy.supermarket.supermarket.purchases.controller;

import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketAddDto;
import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketDto;
import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketGetDto;
import com.academy.supermarket.supermarket.purchases.model.entities.SuperMarket;
import com.academy.supermarket.supermarket.purchases.model.param.FilterParam;
import com.academy.supermarket.supermarket.purchases.model.param.PageParam;
import com.academy.supermarket.supermarket.purchases.service.SuperMarketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/supermarket")
public class SuperMarketController {
    private SuperMarketService superMarketService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public SuperMarketController(SuperMarketService superMarketService) {
        this.superMarketService = superMarketService;
    }

    @PostMapping
    public ResponseEntity<SuperMarketDto> createUser(@Valid @RequestBody SuperMarketDto superMarketDto) {
        SuperMarket savedSuperMarket = superMarketService.createSuperMarket(modelMapper.map(superMarketDto, SuperMarket.class));
        return new ResponseEntity(modelMapper.map(savedSuperMarket, SuperMarketDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<SuperMarketAddDto> addItemsToSupermarket(@PathVariable(value = "id") int id, @Valid @RequestBody SuperMarketAddDto superMarketAddDto) {
        SuperMarket superMarket = superMarketService.addItemsToSupermarket(id, superMarketAddDto);
        return new ResponseEntity<>(modelMapper.map(superMarket, SuperMarketAddDto.class), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<SuperMarketGetDto>> getSupermarketById(@RequestParam(value = "searchBy", required = false) String searchBy,
                                                                      @RequestParam(value = "searchText", required = false) Integer searchText,
                                                                      @RequestParam(value = "limit") @Min(value = 1, message = "Page limit less than one!") int limit,
                                                                      @RequestParam(value = "page") @Min(value = 1, message = "Page size less than one!") int page) {
        FilterParam filterParam = new FilterParam(searchBy, searchText);
        PageParam pageParam = new PageParam(limit, page);
        Page<SuperMarket> superMarkets = this.superMarketService.getAll(pageParam, filterParam);
        Page<SuperMarketGetDto> dtoPage = superMarkets.map(superMarket -> modelMapper.map(superMarket, SuperMarketGetDto.class));
        List<SuperMarketGetDto> superMarketGetDtos = dtoPage.getContent();
        return new ResponseEntity<>(superMarketGetDtos, HttpStatus.OK);
    }


}
