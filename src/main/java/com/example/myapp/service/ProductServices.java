package com.example.myapp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.myapp.entity.Product;
import com.example.myapp.repository.ProductRepo;

@Service
public class ProductServices {

	@Autowired
	private ProductRepo repo;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Page<Product> getProductInfo(Pageable pageRequest) {
		//List<Product> list = getProductList();
		String sql = "SELECT * FROM Products";
		List<Product> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
		repo.deleteAll();
		repo.saveAll(list);
		Page<Product> datalist = repo.findAll(pageRequest);
		return datalist;
	}
	
	public List<Product> getProductList() {
		return Arrays.asList(
				new Product(1, "product_01"),
				new Product(2, "product_02"),
				new Product(3, "product_03"),
				new Product(4, "product_04"),
				new Product(5, "product_05"),
				new Product(6, "product_06"),
				new Product(7, "product_07"),
				new Product(8, "product_08"),
				new Product(9, "product_09"),
				new Product(10, "product_10"),
				new Product(11, "product_11"),
				new Product(12, "product_12"),
				new Product(13, "product_13"),
				new Product(14, "product_14"),
				new Product(15, "product_15"),
				new Product(16, "product_16"),
				new Product(17, "product_17"),
				new Product(18, "product_18"),
				new Product(19, "product_19"),
				new Product(20, "product_20"),
				new Product(21, "product_21"),
				new Product(22, "product_22"),
				new Product(23, "product_23"),
				new Product(24, "product_24"),
				new Product(25, "product_25"),
				new Product(26, "product_26"),
				new Product(27, "product_27"),
				new Product(28, "product_28"),
				new Product(29, "product_29"),
				new Product(30, "product_30"),
				new Product(31, "product_31"),
				new Product(32, "product_32"),
				new Product(33, "product_33"),
				new Product(34, "product_34"),
				new Product(35, "product_35"),
				new Product(36, "product_36"),
				new Product(37, "product_37"),
				new Product(38, "product_38"),
				new Product(39, "product_39"),
				new Product(40, "product_40"),
				new Product(41, "product_41"),
				new Product(42, "product_42"),
				new Product(43, "product_43"),
				new Product(44, "product_44"),
				new Product(45, "product_45"),
				new Product(46, "product_46"),
				new Product(47, "product_47"),
				new Product(48, "product_48"),
				new Product(49, "product_49"),
				new Product(50, "product_50"),
				new Product(51, "product_51"),
				new Product(52, "product_52"),
				new Product(53, "product_53"),
				new Product(54, "product_54"),
				new Product(55, "product_55"),
				new Product(56, "product_56"),
				new Product(57, "product_57"),
				new Product(58, "product_58"),
				new Product(59, "product_59"),
				new Product(60, "product_60"),
				new Product(61, "product_61"),
				new Product(62, "product_62"),
				new Product(63, "product_63"),
				new Product(64, "product_64"),
				new Product(65, "product_65"),
				new Product(66, "product_66"),
				new Product(67, "product_67"),
				new Product(68, "product_68"),
				new Product(69, "product_69"),
				new Product(70, "product_70"),
				new Product(71, "product_71"),
				new Product(72, "product_72"),
				new Product(73, "product_73"),
				new Product(74, "product_74"),
				new Product(75, "product_75"),
				new Product(76, "product_76"),
				new Product(77, "product_77"),
				new Product(78, "product_78"),
				new Product(79, "product_79"),
				new Product(80, "product_80"),
				new Product(81, "product_81"),
				new Product(82, "product_82"),
				new Product(83, "product_83"),
				new Product(84, "product_84"),
				new Product(85, "product_85"),
				new Product(86, "product_86"),
				new Product(87, "product_87"),
				new Product(88, "product_88"),
				new Product(89, "product_89"),
				new Product(90, "product_90"),
				new Product(91, "product_91"),
				new Product(92, "product_92"),
				new Product(93, "product_93"),
				new Product(94, "product_94"),
				new Product(95, "product_95"),
				new Product(96, "product_96"),
				new Product(97, "product_97"),
				new Product(98, "product_98"),
				new Product(99, "product_99"),
				new Product(100, "product_100"));
	}
}