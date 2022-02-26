/**
 * 判断字符串是否为空
 * 	为空，返回true
 * 	不为空，返回false
 * @param str
 * @returns {Boolean}
 */
function isEmpty(str) {
	// trim()方法：去除字符串前后空格
	if (str == null || str.trim() == "") {
		return true;
	}
	return false;
}