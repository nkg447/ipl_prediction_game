package com.ipl.form;

import com.google.gson.JsonElement;

public interface Populatable<T> {
	T populate(JsonElement data);
}
