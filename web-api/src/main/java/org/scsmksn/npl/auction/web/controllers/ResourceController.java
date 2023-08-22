package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.scsmksn.npl.auction.model.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.FORM_ACTION;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE_FILE;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE_NAME;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_RESOURCE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_RESOURCE;

@Controller
@RequestMapping("/resource-management")
public class ResourceController extends GenericWebController {

    @GetMapping("/image")
    public String uploadImage(final Model model) {
        model.addAttribute(FORM_ACTION, "/resource-management/image");
        return "image-upload";
    }

    @GetMapping("/image/{imageId}")
    public String updateImage(@PathVariable(name = "imageId") final Long imageId, final Model model) {
        model.addAttribute(FORM_ACTION, "/resource-management/image/" + imageId);
        return "image-upload";
    }

    @DeleteMapping("/image/{imageId}")
    public String deleteImage(@PathVariable(name = IMAGE_ID) final Long imageId) {
        getTemplate().sendBody(DELETE_RESOURCE, imageId);
        return "redirect:/home";
    }

    @PostMapping("/image")
    public String uploadImage(@RequestParam(IMAGE_NAME) final String imageName
            , @RequestParam(IMAGE_FILE) final MultipartFile imageFile) throws IOException {
        getTemplate().sendBody(SAVE_RESOURCE, geUpdatedImage(imageName, imageFile, new Resource()));
        return "redirect:/home";
    }

    @PutMapping("/image/{imageId}")
    public String updateImage(@PathVariable(name = IMAGE_ID) final Long imageId
            , @RequestParam(IMAGE_NAME) final String imageName, @RequestParam(IMAGE_FILE) final MultipartFile imageFile)
            throws IOException {
        final Resource image = new Resource();
        image.setId(imageId);
        getTemplate().sendBody(SAVE_RESOURCE, geUpdatedImage(imageName, imageFile, image));
        return "redirect:/home";
    }
}
