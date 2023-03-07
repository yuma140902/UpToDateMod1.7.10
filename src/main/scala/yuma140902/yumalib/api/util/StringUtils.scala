package yuma140902.yumalib.api.util

object StringUtils {
  def suffix(delimiter: String, value: String): String =
    if (value.isEmpty) ""
    else delimiter + value

  def prefix(value: String, delimiter: String): String =
    if (value.isEmpty) ""
    else value + delimiter
}
