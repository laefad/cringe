package book_service.service;

// import java.util.List;
// import java.util.ArrayList;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import book_service.model.User;
// import lombok.AccessLevel;
// import lombok.experimental.FieldDefaults;

// @Service
// @FieldDefaults(level = AccessLevel.PRIVATE)
// public class UserDetailsServiceImpl implements UserDetailsService {

//     @Autowired
//     UserService userService;

//     /// Используется ID обернутый в строку, чтобы совпадать с интерфейсом
//     @Override
//     public UserDetails loadUserByUsername(
//         String userID
//     ) throws UsernameNotFoundException {
//         long ID = Long.parseLong(userID);
//         User user = userService.getUserById(ID);

//         if (user == null)
//             throw new UsernameNotFoundException("User " + userID + " not found in database");

//         String role = "ROLE_" + user.getRole();
//         List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//         GrantedAuthority authority = new SimpleGrantedAuthority(role);
//         grantList.add(authority);

//         return new org.springframework.security.core.userdetails.User(
//                 user.getLogin(),
//                 user.getPassword(),
//                 grantList
//         );
//     }
// }
