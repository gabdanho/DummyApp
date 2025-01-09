package com.example.authapp.model.fake

import com.example.authapp.model.Comment
import com.example.authapp.model.Post
import com.example.authapp.model.PostComments
import com.example.authapp.model.User
import com.example.authapp.model.UserList
import com.example.authapp.model.UserLogin
import com.example.authapp.model.UserPosts

object FakeDataClass{
    val fakeCurrentUser = User(
        id = 0,
        firstName = "First Name",
        lastName = "Last Name",
        username = "user",
        age = "18",
        gender = "male",
        birthDate = "01.01.01",
        image = "",
        university = "Great University",
        department = "Department"
    )

    val fakeUserLogin = UserLogin(
        id = 0,
        username = "",
        email = "pochta@mail.com",
        firstName = "First Name",
        lastName = "Last Name",
        gender = "male",
        image = "",
        token = ""
    )

    val fakeUserPosts = UserPosts(
        posts = listOf(
            Post(
                id = 1,
                title = "Title 1",
                body = "Body body body body body body body body body body body body body body ",
                userId = 1,
                tags = listOf(
                    "tag1", "tag2", "tag3"
                )
            ),
            Post(
                id = 2,
                title = "Title 2",
                body = "Body body body body body body body body body body body body body body ",
                userId = 2,
                tags = listOf(
                    "tag4", "tag5", "tag6"
                )
            ),
            Post(
                id = 3,
                title = "Title 3",
                body = "Body body body body body body body body body body body body body body ",
                userId = 3,
                tags = listOf(
                    "tag7", "tag8", "tag9"
                )
            )
        )
    )

    val fakePost = Post(
        id = 1,
        title = "Title 1",
        body = "Body body body body body body body body body body body body body body ",
        userId = 1,
        tags = listOf(
            "tag1", "tag2", "tag3"
        )
    )

    val emptyUserList = UserList(
        users = listOf(
            fakeCurrentUser
        ),
        total = 1
    )

    val commentsPost = PostComments(
        comments = listOf(
            Comment(
                id = 1,
                user = fakeCurrentUser,
                body = "Lorem ipsum"
            ),
            Comment(
                id = 2,
                user = fakeCurrentUser,
                body = "Lorem ipsum ipsum"
            ),
            Comment(
                id = 3,
                user = fakeCurrentUser,
                body = "Lorem ipsum"
            )
        ),
        total = 3
    )
}