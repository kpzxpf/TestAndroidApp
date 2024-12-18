package com.example.test.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.test.data.SupabaseInitializer;
import com.example.test.data.SupabaseUserApi;
import com.example.test.model.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class UserServiceTest {

    @Mock
    private SupabaseUserApi mockApiService;

    @Mock
    private Call<List<User>> mockListUserCall;

    @Mock
    private Call<User> mockUserCall;

    @InjectMocks
    private UserService userService;

    private MockedStatic<SupabaseInitializer> mockedStatic;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockedStatic = Mockito.mockStatic(SupabaseInitializer.class);
        mockedStatic.when(SupabaseInitializer::getClient).thenReturn(mock(Retrofit.class));
        when(SupabaseInitializer.getClient().create(SupabaseUserApi.class)).thenReturn(mockApiService);
        when(mockApiService.getUsers(anyString(), anyString())).thenReturn(mockListUserCall);

        userService = new UserService();
    }

    @Test
    public void getUsers_shouldCallApi() {
        Callback<List<User>> mockCallback = mock(Callback.class);
        userService.getUsers(mockCallback);

        verify(mockApiService).getUsers(anyString(), anyString());
        verify(mockListUserCall).enqueue(mockCallback);
    }

    @Test
    public void getUserById_shouldCallApi() {
        when(mockApiService.getUserById(anyString(), anyString(), anyString()))
                .thenReturn(mockListUserCall);

        Callback<List<User>> mockCallback = mock(Callback.class);
        long userId = 123;
        userService.getUserById(userId, mockCallback);

        verify(mockApiService).getUserById(anyString(), anyString(), eq("eq.123"));
        verify(mockListUserCall).enqueue(mockCallback);
    }

    @Test
    public void createUser_shouldCallApi() {
        User user = new User();
        when(mockApiService.addUser(anyString(), anyString(), eq(user)))
                .thenReturn(mockUserCall);

        Callback<User> mockCallback = mock(Callback.class);
        userService.createUser(user, mockCallback);

        verify(mockApiService).addUser(anyString(), anyString(), eq(user));
        verify(mockUserCall).enqueue(mockCallback);
    }

    @Test
    public void existsUserPassEmail_shouldCallApi() {
        when(mockApiService.existsUserPasswordAndEmail(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(mockListUserCall);

        Callback<List<User>> mockCallback = mock(Callback.class);
        String email = "test@example.com";
        String password = "password123";
        userService.existsUserPassEmail(email, password, mockCallback);

        verify(mockApiService).existsUserPasswordAndEmail(anyString(), anyString(), eq("eq.test@example.com"), eq("eq.password123"));
        verify(mockListUserCall).enqueue(mockCallback);
    }

    @AfterEach
    void tearDown() {
        mockedStatic.close();
    }
}