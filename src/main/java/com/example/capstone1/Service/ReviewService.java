package com.example.capstone1.Service;

import com.example.capstone1.Model.User;
import com.example.capstone1.Model.Review;
import com.example.capstone1.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ProductService productService;



    public List<Review> getReviewRepository() {
        return reviewRepository.findAll();
    }

    public ArrayList<Review> getReviewsByUserID(int userID) {
        ArrayList<Review> reviewsByUserID = new ArrayList<>();
        for (User user : userService.users){
            if (user.getId() == userID){
                for (Review review : reviewRepository.findAll()){
                    if (review.getUserID() == userID){
                        reviewsByUserID.add(review);
                    }
                }
            }
        }
        return reviewsByUserID;
    }

    public String updateReview(Review newReview, int reviewId, int userId) {
        Review review = reviewRepository.findById(reviewId).get();
        for (User user : userService.users){
            if (user.getId() == userId){
                for (int i = 0; i < reviewRepository.count(); i++){
                    if (reviewRepository.getById(i).getId() == reviewId){
                        //Check if it is the author
                        if (reviewRepository.getById(i).getUserID() == userId){
                            review.setTitle(newReview.getTitle());
                            review.setDescription(newReview.getDescription());
                            review.setRating(newReview.getRating());
                            reviewRepository.save(review);
                            return "Review updated successfully";
                        }
                        return "You are not allowed to update this review";
                    }
                }
                return "Review not found";
            }
        }
        return "User not found";
    }

    public String deleteReview(int reviewId, int userId) {
        for (User user : userService.users){
            if (user.getId() == userId){
                for (Review review : reviewRepository.findAll()){
                    if (review.getId() == reviewId){
                        //Check if it is the author
                        if (user.getRole().equals("admin")){
                            reviewRepository.delete(review);
                            return "Review removed successfully";
                        }
                        return "You are not allowed to remove this review";
                    }
                }
                return "Review not found";
            }
        }
        return "User not found";
    }
}
