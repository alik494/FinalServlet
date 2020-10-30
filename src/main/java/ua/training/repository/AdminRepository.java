package ua.training.repository;

import java.util.List;

public interface AdminRepository {
    void changeUserStatus(Long userId);
    void changeUserRole(Long userId);
    void ActivateUserActivate(Long activityId);

    List getAllNotActive();
}
