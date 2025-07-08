package com.lawyer.service;

import com.lawyer.entity.Register;

public interface ProfileService {
    Register profile(String token);
}
