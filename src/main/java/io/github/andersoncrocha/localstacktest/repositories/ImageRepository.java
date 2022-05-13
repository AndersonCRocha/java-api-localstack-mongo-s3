package io.github.andersoncrocha.localstacktest.repositories;

import io.github.andersoncrocha.localstacktest.models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {

}
