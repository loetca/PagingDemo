package cn.loetca.pagingdemo.bean;

import java.util.List;

public class Page<T> {
	private Integer pageNo; // 当前页码
	public static final Integer SHOW_ITEMS = 5; // 每页显示多少信息
	private Integer countNo; // 数据库中一共有多少符合条件的记录
	@SuppressWarnings("unused")
	private Integer totalPageNo; // 一共有多少页码
	private List<T> list; // 把查询出来的记录保存到List集合中

	public Integer getPageNo() {
		// 如果当前页码小于1，则返回1
		if (pageNo < 1) {
			return 1;
		}

		// 如果当前页码大于总的页码，则返回总的页码
		if (pageNo > getTotalPageNo()) {
			// 如果总的页码等于0【当价格查询时，没有符合条件的情况】，则返回1
			if (0 == getTotalPageNo()) {
				return 1;
			}

			// 总的页码不等于0，返回总的页码
			return getTotalPageNo();
		}

		// 其他情况就正常返回
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getCountNo() {
		return countNo;
	}

	public void setCountNo(Integer countNo) {
		this.countNo = countNo;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getTotalPageNo() {
		// 如果总的记录数能够被每页显示的数量整除，就返回整除的结果
		if (0 == getCountNo() % SHOW_ITEMS) {
			return getCountNo() / SHOW_ITEMS;
		}

		// 如果不能被整除，就在除以的结果上 + 1
		return getCountNo() / SHOW_ITEMS + 1;
	}

}
