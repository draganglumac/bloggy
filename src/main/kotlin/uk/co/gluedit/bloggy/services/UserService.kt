package uk.co.gluedit.bloggy.services

import org.springframework.stereotype.Component
import uk.co.gluedit.bloggy.model.User
import java.util.*

@Component
class UserService {
    private val users: MutableList<User> = mutableListOf(
            User(1, "Adam", Date()),
            User(2, "Eve", Date()),
            User(3, "Jack", Date()),
    )

    fun findAll(): List<User> = users

    fun save(user: User): User {
        val u = if (user.id != null) user else user.copy(id = users.size + 1)
        users.add(u)
        return u
    }

    fun findOne(id: Int): User? = users.find { user -> user.id == id }

    fun deleteById(userId: Int): User? {
        val iter = users.iterator()
        while (iter.hasNext()) {
            val user = iter.next()
            if (user.id == userId) {
                iter.remove()
                return user
            }
        }
        return null
    }
}