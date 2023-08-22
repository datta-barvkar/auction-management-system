package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.scsmksn.npl.auction.model.Params;
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
import java.util.HashMap;
import java.util.Map;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.AUCTION_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.FORM_ACTION;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE_FILE;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE_NAME;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.TEAM_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_RESOURCE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_RESOURCE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.UPDATE_IMAGE_ID_FOR_TEAM;

@Controller
@RequestMapping("/resource-management/auction")
public class AuctionResourceController extends GenericWebController {
    private String appLogoName;

    @GetMapping("/{auctionId}/image")
    public String uploadImage(@PathVariable(name = AUCTION_ID) final Long auctionId, final Model model) {
        model.addAttribute(FORM_ACTION, "/resource-management/auction/" + auctionId + "/image");
        return "image-upload";
    }

    @GetMapping("/{auctionId}/image/{imageId}")
    public String updateImage(@PathVariable(name = AUCTION_ID) final Long auctionId
            , @PathVariable(name = IMAGE_ID) final Long imageId, final Model model) {
        model.addAttribute(FORM_ACTION, "/resource-management/auction/" + auctionId + "/image/" + imageId);
        return "image-upload";
    }

    @DeleteMapping("/{auctionId}/image/{imageId}")
    public String deleteImage(@PathVariable(name = AUCTION_ID) final Long auctionId
            , @PathVariable(name = IMAGE_ID) final Long imageId) {
        getTemplate().sendBody(DELETE_RESOURCE, imageId);
        getTemplate().sendHeader(UPDATE_IMAGE_ID_FOR_TEAM, TEAM_ID, auctionId);
        return "redirect:/auction-management/auction/" + auctionId;
    }

    @PostMapping("/{auctionId}/image")
    public String uploadImage(@PathVariable(name = AUCTION_ID) final Long auctionId
            , @RequestParam(IMAGE_NAME) final String imageName, @RequestParam(IMAGE_FILE) final MultipartFile imageFile)
            throws IOException {
        final Resource image = getTemplate().requestWithBody(SAVE_RESOURCE
                , geUpdatedImage(imageName, imageFile, new Resource()), Resource.class);
        final Params params = Params.getParamsBuilder().putJpaQueryParam(IMAGE_ID, image.getId())
                .putJpaQueryParam(TEAM_ID, auctionId).build();
        getTemplate().sendHeaders(UPDATE_IMAGE_ID_FOR_TEAM, params);
        return "redirect:/auction-management/auction/" + auctionId;
    }

    @PutMapping("/{auctionId}/image/{imageId}")
    public String updateImage(@PathVariable(name = AUCTION_ID) final Long auctionId
            , @PathVariable(name = IMAGE_ID) final Long imageId, @RequestParam(IMAGE_NAME) final String imageName
            , @RequestParam(IMAGE_FILE) final MultipartFile imageFile) throws IOException {
        final Resource image = new Resource();
        image.setId(imageId);
        getTemplate().sendBody(SAVE_RESOURCE, geUpdatedImage(imageName, imageFile, image));
        return "redirect:/auction-management/auction/" + auctionId;
    }
}
