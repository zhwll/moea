package com.xfactor.moea.main.model;

import java.net.URL;
import java.time.LocalDate;
import javafx.beans.property.*;

/**
 * Project ���ʾһ���Ż���Ŀ
 * 
 * @author liu.yijun
 * @date 2020/09/02
 *
 */
public class Project {
	private SimpleStringProperty name; // ��Ŀ��
	private LocalDate creationDate; // ������Ŀ����
	private LocalDate lastModifiedDate; // ���һ���޸�����
	private URL localDir; // �ļ�ϵͳ�е�·��
	private SimpleDoubleProperty size; // ��Ŀ�ļ��Ĵ�С

	/**
	 * Ĭ�Ϲ��캯�� 
	 */
	public Project() {
	}

	/**
	 * ���û�����һ���µ���Ŀʱ��Ӧ�õ���������캯�� 
	 */
	public Project(SimpleStringProperty name, LocalDate date) {
	}

	public SimpleStringProperty getProjectName() {
		return this.name;
	}

	public LocalDate getProjectCreationDate() {
		return this.creationDate;
	}

	public LocalDate getProjectLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public URL getProjectLocalDir() {
		return this.localDir;
	}

	public SimpleDoubleProperty getProjectSize() {
		return size;
	}

}