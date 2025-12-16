package com.dog.contactapi.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dog.contactapi.domain.Contact;
import com.dog.contactapi.repo.ContactRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.dog.contactapi.constant.Constant.PHOTO_DIRECTORY;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepo contactRepo;

    public Page<Contact> getAllContacts(int page, int size) {
        log.info("Fetching all contacts with pagination: {}", page, size);
        return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public Contact getContact(String id) {
        log.info("Fetching contact with ID: {}", id);
        return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public Contact createContact(Contact contact) {
        log.info("Creating new contact: {}", contact);
        return contactRepo.save(contact);
    }

    public void deleteContact(String id) {
        log.info("Deleting contact with ID: {}", id);
        contactRepo.deleteById(id);
    }

    public String uploadPhoto(String id, MultipartFile file) {
        log.info("Uploading photo for contact ID: {}", id);
        String photoUrl = uploadFunction.apply(id, file);
        Contact contact = getContact(id);
        contact.setPhotoUrl(photoUrl);
        contactRepo.save(contact);
        return photoUrl;
    }

    private final Function<String, String> fileExtension = filename ->  Optional.of(filename)
            .filter(name -> name.contains("."))
            .map(name -> name.substring(filename.lastIndexOf(".")))
            .orElse(".png");

    private final BiFunction<String, MultipartFile, String> uploadFunction = (id, imageFile) -> {
        log.info("Uploading photo for contact ID: {}", id);
        // Simulate photo upload and return URL
        try {
            Path fileLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileLocation)) {
                Files.createDirectories(fileLocation);
            }
            String filename = id + fileExtension.apply(imageFile.getOriginalFilename());
            Files.copy(imageFile.getInputStream(), fileLocation.resolve(filename),REPLACE_EXISTING);
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/contacts/image/" + filename)
                    .toUriString();
        } catch (Exception e) {
            throw new RuntimeException("Photo upload failed", e);
        }
    };
}
