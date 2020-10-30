package ua.training.services;

import ua.training.domain.Activity;

import java.util.List;

public interface AdminService {
    void changeUserStatus(Long userId);
    void changeUserRole(Long userId);
    void ActivateUserActivate(Long ActivityId);

    List<Activity> getAllNotActive();
}
