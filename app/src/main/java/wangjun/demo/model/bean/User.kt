package wangjun.demo.model.bean


/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/27
 *   desc : User数据类
 * </pre>
 */
data class User(val admin: Boolean,
                val chapterTops: List<String>,
                val collectIds: List<Int>,
                val email: String,
                val icon: String,
                val id: Int,
                val nickname: String,
                val password: String,
                val publicName: String,
                val token: String,
                val type: Int,
                val username: String){

    override fun equals(other: Any?): Boolean {
        return if (other is User){
            this.id == other.id
        }else false
    }
}