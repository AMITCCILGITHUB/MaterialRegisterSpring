package org.map.service;

import javafx.concurrent.Task;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.utils.MaterialData;
import org.springframework.stereotype.Repository;

@Repository("PersistMaterialDetails")
public class PersistMaterialDetails extends AbstractService<MaterialMaster, Void> {

	@Resource(name = "MaterialData")
	private MaterialData materialData;

	MaterialMaster material;
	PersistType persistType;

	public PersistMaterialDetails() {

		this.material = new MaterialMaster();
		this.persistType = PersistType.NONE;
	}
	
	public PersistMaterialDetails(MaterialMaster material,
			PersistType persistType) {

		this.material = material;
		this.persistType = persistType;
	}

	@Override
	public Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				switch (persistType) {
				case INSERT:
					materialData.insertMaterial(material);
					break;
				case UPDATE:
					materialData.updateMaterial(material);
					break;
				case DELETE:
					materialData.deleteMaterial(material);
					break;
				default:
					break;
				}

				return null;
			}
		};
	}

	public MaterialData getMaterialData() {
		return materialData;
	}

	public void setMaterialData(MaterialData materialData) {
		this.materialData = materialData;
	}

	public MaterialMaster getMaterial() {
		return material;
	}

	public void setMaterial(MaterialMaster material) {
		this.material = material;
	}

	public PersistType getPersistType() {
		return persistType;
	}

	@Override
	public void setPersistType(PersistType persistType) {
		this.persistType = persistType;
	}

	@Override
	public void setPersistEntity(MaterialMaster entity) {
		this.material = entity;
	}
}