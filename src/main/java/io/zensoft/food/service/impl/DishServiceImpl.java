package io.zensoft.food.service.impl;

import io.zensoft.food.domain.DishCreateRequest;
import io.zensoft.food.domain.DishUpdateRequest;
import io.zensoft.food.model.Cafe;
import io.zensoft.food.model.Dish;
import io.zensoft.food.repository.CafeRepository;
import io.zensoft.food.repository.DishRepository;
import io.zensoft.food.service.AmazonClient;
import io.zensoft.food.service.DishService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    private CafeRepository cafeRepository;

    private AmazonClientServiceImpl amazonClient;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository,
                           CafeRepository cafeRepository,
                           AmazonClientServiceImpl amazonClient) {
        this.dishRepository = dishRepository;
        this.cafeRepository = cafeRepository;
        this.amazonClient = amazonClient;
    }

    @Override
    public List<Dish> getAllByCafeId(@NonNull Long cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new EntityNotFoundException("Cafe with id = " + cafeId + " not found."));
        return cafe.getDishes();
    }

    @Override
    public Dish getById(@NonNull Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dish with id = " + id + " not found"));
    }

    @Transactional
    @Override
    public Dish update(@NonNull DishUpdateRequest request, MultipartFile multipartFile) {

        if (!dishRepository.existsById(request.getId())) {
            throw new EntityNotFoundException("Dish with id = " + request.getId() + " not found.");
        }

        Dish dish = new Dish();
        String fileUrl = amazonClient.generateFileName(multipartFile);
        dish.setId(request.getId());
        dish.setName(request.getName());
        dish.setPortion(request.getPortion());
        dish.setPrice(request.getPrice());
        dish.setCafe(request.getCafe());
        dish.setImageUrl(fileUrl);
        return dishRepository.save(dish);
    }

    @Override
    public void delete(@NonNull Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dish with id = " + id + " not found."));
        String url = dish.getImageUrl();
        amazonClient.deleteFileFromS3Bucket(url);
        dishRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Dish add(@NonNull DishCreateRequest request,
                    MultipartFile multipartFile) {
        amazonClient.uploadFile(multipartFile);
        String fileUrl = amazonClient.getEndpointUrl() + "/"
                + amazonClient.getBucketName() + "/"
                + amazonClient.generateFileName(multipartFile);
        Dish dish = new Dish();
        dish.setName(request.getName());
        dish.setPrice(request.getPrice());
        dish.setPortion(request.getPortion());
        dish.setCafe(request.getCafe());
        dish.setImageUrl(fileUrl);
        return dishRepository.save(dish);
    }
}
