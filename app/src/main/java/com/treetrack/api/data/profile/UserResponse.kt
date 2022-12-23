package com.treetrack.api.data.profile

import com.treetrack.profile.User

data class UserResponse(
    val ID: Int,
    val department_id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val location: String?,
    val phone_number: String?,
    val type: Int,
    val image: String?
) {
    //like static in Java
    companion object {
        fun getUserFromUserResponse(userResponse: UserResponse): User {
            return User(
                ID = userResponse.ID,
                email = userResponse.email,
                first_name = userResponse.first_name,
                last_name = userResponse.last_name,
                location = userResponse.location,
                phone_number = userResponse.phone_number,
                image = "https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png"
            )
        }
    }
}
