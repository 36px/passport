package net36px.passport.boot.data.service;

import java.util.List;

public interface RandomStringService {

	String nextString(int length, List<String> salt);

}
