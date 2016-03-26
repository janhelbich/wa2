package org.wa2.hw1.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.wa2.hw1.model.AbstractEntity;

public class GenericStoreService {

	public <E extends AbstractEntity> void save(AbstractEntity e) {
		if (e.getId() == null) {
			e.setId(getNextSeq(e.getClass()));
		}
		try {
			FileOutputStream fos = new FileOutputStream(getResourceLocation(e));
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(e);
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public <E extends AbstractEntity> void delete(E e) {
		try {
			Files.delete(Paths.get(getResourceLocation(e)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public <E extends AbstractEntity> E find(Class<E> clazz, Long id) {
		try {
			FileInputStream fis = new FileInputStream(getResourceLocation(clazz, id));
	        BufferedInputStream bis = new BufferedInputStream(fis);
	        ObjectInputStream ois = new ObjectInputStream(bis);
	        
	        @SuppressWarnings("unchecked")
			E obj = (E) ois.readObject();
	        ois.close();
	        
	        return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set<Long> listIds(Class<? extends AbstractEntity> clazz) {
		Set<Long> ids = new HashSet<>();
		try {
			Files.walk(Paths.get(getStoreLocation(clazz)))
					.filter(f -> Files.isRegularFile(f)).forEach(f -> {
						ids.add(Long.parseLong(f.getFileName().toString()));
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ids;
	}

	protected <E extends AbstractEntity> String getStoreLocation(Class<E> clazz) {
		return clazz.getSimpleName().toLowerCase();
	}

	protected String getResourceLocation(Class<? extends AbstractEntity> clazz, Long id) {
		return getStoreLocation(clazz) + "/" + id;
	}

	protected String getResourceLocation(AbstractEntity e) {
		return getResourceLocation(e.getClass(), e.getId());
	}

	protected Long getNextSeq(Class<? extends AbstractEntity> clazz) {
		Set<Long> ids = listIds(clazz);
		if (ids.isEmpty()) {
			return 1L;
		}
		
		return ids.stream().max(Long::compare).get() + 1;
	}

}
