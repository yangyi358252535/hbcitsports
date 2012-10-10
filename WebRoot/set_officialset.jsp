﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
<link href="css/subcss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath }/js/jquery-1.6.min.js"></script>
<title>大会主席团及工作人员</title>
<script type="text/javascript">
function alterpws()
		{
		    
			var presidium=$('#presidium').val();
			var org_committee_1=$('#org_committee_1').val();
			var org_committee_2=$('#org_committee_2').val();
			var org_committee_3=$('#org_committee_3').val();
			var  secretariat_1=$('#secretariat_1').val();
			var  secretariat_2=$('#secretariat_2').val();
			var  secretariat_3=$('#secretariat_3').val();
			var  secretariat_4=$('#secretariat_4').val();
			var  secretariat_5=$('#secretariat_5').val();
			var  secretariat_6=$('#secretariat_6').val();
			var  secretariat_7=$('#secretariat_7').val();
			var  arbitration=$('#arbitration').val();
			
			$.ajax({
					url :"${pageContext.request.contextPath }/servlet/AddOfficialSetServlet",
					type : 'post',
				   	data:{presidium:presidium,org_committee_1:org_committee_1,org_committee_2:org_committee_2,
					org_committee_3:org_committee_3,secretariat_1:secretariat_1,secretariat_2:secretariat_2,
					secretariat_3:secretariat_3,secretariat_4:secretariat_4,secretariat_5:secretariat_5,
					secretariat_6:secretariat_6,secretariat_7:secretariat_7,arbitration:arbitration},
					
					success :function(mm){
							var revalue=mm.replace(/\r\n/g,'');
							if(revalue=="success")
							{
								alert("添加成功!",function(){window.location.reload();});
							}
							else
								alert("添加失败!");
						}
					});	
		}
		
		
	
		
</script>
<script type="text/javascript">
//隔行变色
	$(document).ready(function(){
			
			 $(".stripe_tb tr").mouseover(function(){ //如果鼠标移到class为stripe_tb的表格的tr上时，执行函数
			 $(this).addClass("over");}).mouseout(function(){ //给这行添加class值为over，并且当鼠标一出该行时执行函数
			 $(this).removeClass("over");}) //移除该行的class
			 $(".stripe_tb tr:even").addClass("alt"); //给class为stripe_tb的表格的偶数行添加class值为alt
			
		});
</script>
<style>
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.pageTitle {
	color: #e1e2e3;
	font-size: 12px;
}
</style>

	</head>
  
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="pageTitle">赛前设置-->添加工作人员设置</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
 <table width="55%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="stripe_tb" align="center">
					<tr class="tableContent" >
						<td >
							大会主席团：
							</td>
							<td>
						 <textarea name="presidium" id="presidium" cols="45" rows="5" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"></textarea>
					    </td>
                    </tr>
					<tr class="tableContent" >
						<td>
							大会组委会主任：
							</td>
							<td>
							<input type="text" name="org_committee_1" id="org_committee_1" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
					<tr class="tableContent" >
						<td>
							大会组委会副主任：
							</td>
							<td>
							<input type="text" name="org_committee_2" id="org_committee_2" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							大会组委委员:
							</td>
							<td>
				<textarea name="org_committee_3" id="org_committee_3" cols="45" rows="5" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"></textarea>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							大会秘书长：
							</td>
							<td>
							<input type="text" name="secretariat_1" id="secretariat_1" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							大会副秘书长：
							</td>
							<td>
							<input type="text" name="secretariat_2" id="secretariat_2" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							会务组负责人：
							</td>
							<td>
							<input type="text" name="secretariat_3" id="secretariat_3" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							宣传组负责人：
							</td>
							<td>
							<input type="text" name="secretariat_4" id="secretariat_4" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							奖品组负责人：
							</td>
							<td>
							<input type="text" name="secretariat_5" id="secretariat_5" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							保卫组负责人 ：
							</td>
							<td>
							<input type="text" name="secretariat_6" id="secretariat_6" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							后勤保障组负责人：
							</td>
							<td>
							<input type="text" name="secretariat_7" id="secretariat_7" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"/>
						</td>
					</tr>
                    	<tr class="tableContent" >
						<td>
							大会仲裁委员会：
							</td>
							<td>
							<textarea name="arbitration" id="arbitration" cols="45" rows="5" onKeyUp="this.value=this.value.replace(/[， ]/g,',')"></textarea>
						</td>
					</tr>
						<tr class="tableContent" >
					<td colspan="3" >
					 <input type="button" name="button" id="button" value="提交" onclick="alterpws()">
  <input type="button" value="修改" onclick="window.location.href='${pageContext.request.contextPath }/servlet/SelectAllOfficialBySportsidServlet?sportsid==sportsid}'">
					</td>
					</tr>
			</table>
			</td>
			</tr>
			</table>
</body>
</html>
