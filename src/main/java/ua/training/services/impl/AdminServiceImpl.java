package ua.training.services.impl;

import ua.training.domain.Activity;
import ua.training.repository.AdminRepository;
import ua.training.repository.impl.AdminRepositoryImpl;
import ua.training.services.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminRepository adminRepository = new AdminRepositoryImpl();

    @Override
    public void changeUserStatus(Long userId) {
        adminRepository.changeUserStatus(userId);
    }

    @Override
    public void changeUserRole(Long userId) {

    }

    @Override
    public void ActivateUserActivate(Long ActivityId) {

    }

    @Override
    public List<Activity> getAllNotActive() {
        return adminRepository.getAllNotActive();
    }
}
