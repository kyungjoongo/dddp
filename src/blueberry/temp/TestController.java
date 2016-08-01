package blueberry.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import blueberry.common.PageUtil;
import blueberry.user.UserVO;


@SuppressWarnings({"rawtypes", "unused","unchecked"} )
@Controller
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private TestService service;

	

	@RequestMapping(value = "/selectAdminUser.do", method = RequestMethod.GET)
	public ModelAndView listQuery(ModelMap model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		
		List arrList = new ArrayList();
		arrList = service.selectAdminUser();
		mav.setViewName("/temp/list");
		return mav;
	}
	

	@RequestMapping(value = "/jqgridsssideExam.do", method = RequestMethod.GET)
	public ModelAndView jqgridsssideExam(ModelMap model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		
		List arrList = new ArrayList();
		arrList = service.selectAdminUser();
		mav.setViewName("/temp/jqgridsssideExam");
		return mav;
	}
	
	
	@RequestMapping(value="/list.do")
	public @ResponseBody HashMap list(
		
		@RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
		@RequestParam(value = "sortOrder", defaultValue = "") String sortOrder,
		@RequestParam(value = "sortColumnName", defaultValue = "") String sortColumnName
		
		
		)
		{
	    	
		HashMap jsonObject = new HashMap();

		
		
		int totalRowCount = service.selectAdminUserTotCnt();
		
		
		PageUtil pu = new PageUtil(page, totalRowCount, pageSize, 10);
		HashMap pagemap = new HashMap();
		pagemap.put("startRow", (page-1)*pageSize);
		//pagemap.put("endRow", pu.getEndRow());
		
		pagemap.put("pageSize", pageSize);
		pagemap.put("sortOrder", sortOrder);
		pagemap.put("sortColumnName", sortColumnName);
		
		String[] tempArr=null;
		
		String[] tempArr2= { "test", "test2"};
		
		pagemap.put("tempArr", tempArr2);
		
		
		List<HashMap> arrList= service.selectAdminUserPaging(pagemap);
		
		for (HashMap mapone : arrList){
		    
		  //  mapone
		}
		
		
		jsonObject.put("pu", pu);
		jsonObject.put("rows", arrList);
		
		
		 /*total = response.TotalPages,
		                page = request.page,
		                records = response.TotalRecords,
		                rows = response.Rows*/
		
		
		jsonObject.put("page", page);
		int totalPageCnt  = pu.getTotalPageCount();
		//占쎈꽅占쎄퉱 占쎈읂占쎌뵠筌욑옙
		jsonObject.put("total", totalPageCnt);
		
		//占쎈꽅占쎄퉱 占쎌쟿�굜遺얜굡占쎈뮞.
		jsonObject.put("records", totalRowCount);
		jsonObject.put("success", true);
		
		
		System.out.println("list==============================>");
		
		return jsonObject; 
	}
	
	
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public @ResponseBody HashMap add(
			@ModelAttribute TestVO vo
	) {

		ModelAndView mv = new ModelAndView();
		HashMap jsonObject = new HashMap();

		int result = service.add(vo);
		
		jsonObject.put("result", result);
		return jsonObject; 
	}
	
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public @ResponseBody HashMap delete(
			@RequestParam(value = "selectedRows") String  selectedRows
			
	) {

		ModelAndView mv = new ModelAndView();
		HashMap jsonObject = new HashMap();
		
		String[] arrSelectedRows = selectedRows.split(",");

		int result=0;
		for ( int i=0; i<arrSelectedRows.length; i++){
		
			result = service.delete(arrSelectedRows[i]);
		}
		
		jsonObject.put("result", result);
		return jsonObject; 
	}
	
	@RequestMapping(value = "/selectOne.do", method = RequestMethod.POST)
	public @ResponseBody HashMap selectOne(
			@RequestParam(value = "id") String  id
			
	) {

		ModelAndView mv = new ModelAndView();
		HashMap jsonObject = new HashMap();
		HashMap map = service.selectOne(id);
		
		jsonObject.put("result", map);
		return jsonObject; 
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public @ResponseBody HashMap update(
			@ModelAttribute TestVO vo
	) {

		ModelAndView mv = new ModelAndView();
		HashMap jsonObject = new HashMap();
		int result = service.update(vo);
		jsonObject.put("result", result);
		return jsonObject; 
	}
	
	@RequestMapping(value = "/updateCells.do", method = RequestMethod.POST)
	public @ResponseBody HashMap updateCells(
			@RequestParam(value = "id") Integer id,
		    @RequestParam(value = "cellName") String cellName,
		    @RequestParam(value = "cellValue") String cellValue 
	) {

		ModelAndView mv = new ModelAndView();
		HashMap jsonObject = new HashMap();
		HashMap valueMap = new HashMap();
		valueMap.put("id", id);
		valueMap.put("colNm", cellName);
		valueMap.put("value", cellValue);
		
		int result = service.updateCell(valueMap);
		jsonObject.put("result", result);
		
		return jsonObject;
		
	}
	
	@RequestMapping(value = "/getGrid.do")
	public @ResponseBody HashMap getGrid(
			//@RequestParam(value = "id") String  id
			
	) {

		ModelAndView mv = new ModelAndView();
		HashMap JsonObject = new HashMap();
		HashMap valueMap = new HashMap();
		List arrList=new ArrayList();
		/*'Author',
		 'Title', 'Manufacturer', 'ProductGroup', 'DetailPageURL' */
		valueMap.put("Author", "고경준");
		valueMap.put("Title", "고경준은 천재인가");
		valueMap.put("Manufacturer", "고경사");
		valueMap.put("ProductGroup", "geniusGroup");
		valueMap.put("DetailPageURL", "http://kyungjoongo.com");
		
		arrList.add(valueMap);
		
		
		HashMap valueMap2 = new HashMap();
		
		valueMap2.put("Author", "고경준2");
		valueMap2.put("Title", "고경준은 천재인가123");
		valueMap2.put("Manufacturer", "고경사23");
		valueMap2.put("ProductGroup", "geniusGrou2323p");
		valueMap2.put("DetailPageURL", "http://kyungjoongo.com2323");
		
		
		arrList.add(valueMap2);
		
		
		
		JsonObject.put("resultList", arrList);
		JsonObject.put("total", arrList.size());
		
		return JsonObject; 
	}




}