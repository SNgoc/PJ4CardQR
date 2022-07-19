-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 17, 2022 at 07:02 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `authdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `back_image` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `delete_at` datetime DEFAULT NULL,
  `front_image` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `secret_seri` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `back_image`, `create_at`, `delete_at`, `front_image`, `name`, `price`, `secret_seri`, `update_at`, `url`) VALUES
(1, NULL, '2022-07-16 17:30:00', NULL, NULL, 'CARD', 70, NULL, NULL, NULL),
(2, NULL, '2022-07-16 17:30:00', NULL, NULL, 'STICKER', 50, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `confirmation_token`
--

CREATE TABLE `confirmation_token` (
  `id` bigint(20) NOT NULL,
  `confirmed_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `expires_at` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `app_user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `confirmation_token`
--

INSERT INTO `confirmation_token` (`id`, `confirmed_at`, `created_at`, `expires_at`, `token`, `app_user_id`) VALUES
(1, NULL, '2022-07-16 01:14:00', '2022-07-16 01:29:00', '711df7f8-5804-487f-8bac-4f45d0058e20', 3),
(2, NULL, '2022-07-16 01:14:04', '2022-07-16 01:29:04', '6b751003-7c32-4c00-b8fd-e40e230261cf', 4),
(3, NULL, '2022-07-16 01:14:30', '2022-07-16 01:29:30', '3409c728-9b23-4a6a-936e-30d29e4c7a03', 5),
(4, NULL, '2022-07-16 01:14:55', '2022-07-16 01:29:55', 'a9497464-f552-467c-b840-accdefd55236', 6),
(5, NULL, '2022-07-16 01:15:20', '2022-07-16 01:30:20', 'c5e0e22a-3fec-406f-9d99-0ef0814b9ea5', 7),
(6, NULL, '2022-07-16 01:15:54', '2022-07-16 01:30:54', 'b0d54c94-6db2-4003-bb44-c0f5b4c3dea5', 8),
(7, NULL, '2022-07-16 01:16:26', '2022-07-16 01:31:26', 'd06b2bca-8c43-4aec-b5e9-536dfd056738', 9);

-- --------------------------------------------------------

--
-- Table structure for table `confirmation_token_sequence`
--

CREATE TABLE `confirmation_token_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `confirmation_token_sequence`
--

INSERT INTO `confirmation_token_sequence` (`next_val`) VALUES
(8);

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `id` bigint(20) NOT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `discount` bigint(20) DEFAULT NULL,
  `required` bigint(20) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `used` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(10);

-- --------------------------------------------------------

--
-- Table structure for table `link_type`
--

CREATE TABLE `link_type` (
  `id` bigint(20) NOT NULL,
  `data_url` varchar(255) DEFAULT NULL,
  `link_image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `placeholder` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `link_type`
--

INSERT INTO `link_type` (`id`, `data_url`, `link_image`, `name`, `placeholder`) VALUES
(1, '', 'https://res.cloudinary.com/tphcm/image/upload/v1657613872/linktype/facebook_fn3mvs.png', 'Facebook', 'Enter your Facebook Link'),
(2, '', 'https://res.cloudinary.com/tphcm/image/upload/v1657613873/linktype/instagram_vfmvaa.png', 'Instagram', 'Enter your Instagram Link'),
(3, '', 'https://res.cloudinary.com/tphcm/image/upload/v1657613872/linktype/telegram_nr2uxs.png', 'Telegram', 'Enter your Telegram Link'),
(4, '', 'https://res.cloudinary.com/tphcm/image/upload/v1657613872/linktype/whatsApp_cpfcbl.png', 'WhatsApp', 'Enter your WhatsApp Link'),
(5, '', 'https://res.cloudinary.com/tphcm/image/upload/v1657613871/linktype/twitter_aqkjiy.png', 'Twitter', 'Enter your Twitter Link'),
(6, '', 'https://res.cloudinary.com/tphcm/image/upload/v1657613871/linktype/phone_ojgmtj.png', 'Phone', 'Enter your Phone number'),
(7, '', 'https://res.cloudinary.com/tphcm/image/upload/v1657613871/linktype/gmail_qbsqox.png', 'Email', 'Enter your Email'),
(8, '', 'https://chungnhaniso.com.vn/wp-content/uploads/icon-web.jpg', 'Url', 'Enter your Link');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `process_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `address`, `fullname`, `phone`, `price`, `category_id`, `process_id`, `product_id`, `user_id`) VALUES
(1, 'Tay Ninh', 'lazada shoppee', '123', 150, 1, 4, 1, 3),
(2, 'Tay Ninh', 'lazada shoppee', '123', 250, 1, 2, 2, 3),
(3, 'Tay Ninh', 'lazada shoppee', '123', 100, 1, 1, 3, 3),
(4, 'Tay Ninh', 'lazada shoppee', '123', 50, 1, 2, 4, 3),
(5, 'Tay Ninh', 'lazada shoppee', '123', 100, 1, 1, 5, 3),
(6, 'address user', 'nero saro', '4515181199229', 55, 1, 3, 6, 8),
(7, 'Tay Ninh', 'sarrow', '123', 99, 1, 3, 7, 9);

-- --------------------------------------------------------

--
-- Table structure for table `order_process`
--

CREATE TABLE `order_process` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `style` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_process`
--

INSERT INTO `order_process` (`id`, `description`, `name`, `style`) VALUES
(1, 'confirm and deliver the order', 'Waiting', 'badge badge-secondary'),
(2, 'complete order', 'Delivery', 'badge badge-primary'),
(3, 'order completed', 'Success', 'badge badge-success'),
(4, 'Cancel this order', 'Cancel', 'badge badge-danger');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `delete_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_urlcode` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `create_at`, `delete_at`, `description`, `image_urlcode`, `name`, `status`, `token`, `update_at`, `url`, `user_id`) VALUES
(1, NULL, NULL, 'Smart Cards', 'product/b8q4wpnyufqymads6mp5', 'Smart Card', 1, '6d903e46-c6ba-45e0-a50c-ba7001ef3188', NULL, 'http://res.cloudinary.com/tphcm/image/upload/v1657908868/product/b8q4wpnyufqymads6mp5.png', 3),
(2, NULL, NULL, 'Smart Cards', 'product/wtnrokyaqr9bfnpoti6h', 'Smart Card', 1, '96614fea-5c06-49c3-aea7-1943811bfca1', NULL, 'http://res.cloudinary.com/tphcm/image/upload/v1657908891/product/wtnrokyaqr9bfnpoti6h.png', 3),
(3, NULL, NULL, 'Smart Cards', 'product/zqhnqrjkr6zcbquxzh7g', 'Smart Card', 1, 'f01c4d87-ed88-4357-97ed-1b0370b8f787', NULL, 'http://res.cloudinary.com/tphcm/image/upload/v1657908929/product/zqhnqrjkr6zcbquxzh7g.png', 3),
(4, NULL, NULL, 'Smart Cards', 'product/hv3e1pjuxikj220kghl7', 'Smart Card', 1, '64326c22-40da-408a-8ca5-f24514ee4248', NULL, 'http://res.cloudinary.com/tphcm/image/upload/v1657908959/product/hv3e1pjuxikj220kghl7.png', 3),
(5, NULL, NULL, 'Smart Cards', 'product/kgqa43yat23euq5lgwir', 'Smart Card', 1, '83e94801-3e15-4207-9e50-adeb1f2e2e6b', NULL, 'http://res.cloudinary.com/tphcm/image/upload/v1657908977/product/kgqa43yat23euq5lgwir.png', 3),
(6, NULL, NULL, 'Smart Cards', 'product/ccaocymwozqvlyqirqhd', 'Smart Card', 1, 'ef25f1fb-d5b6-42f4-a9ee-34e1a5fbd740', NULL, 'http://res.cloudinary.com/tphcm/image/upload/v1657947417/product/ccaocymwozqvlyqirqhd.png', 8),
(7, NULL, NULL, 'Smart Cards', 'product/iu9jrlsmhkdrtmx0o8yk', 'Smart Card', 1, '82f4816e-80db-426e-a389-c2ec57db76b0', NULL, 'http://res.cloudinary.com/tphcm/image/upload/v1658045644/product/iu9jrlsmhkdrtmx0o8yk.png', 9);

-- --------------------------------------------------------

--
-- Table structure for table `reset_password`
--

CREATE TABLE `reset_password` (
  `id` bigint(20) NOT NULL,
  `confirmed_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `expires_at` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `app_user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `delete_at` datetime DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `star` int(11) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_MODERATOR');

-- --------------------------------------------------------

--
-- Table structure for table `socialweb`
--

CREATE TABLE `socialweb` (
  `social_id` bigint(20) NOT NULL,
  `company1` varchar(255) DEFAULT NULL,
  `company2` varchar(255) DEFAULT NULL,
  `facebook` varchar(255) DEFAULT NULL,
  `instagram` varchar(255) DEFAULT NULL,
  `tiktok` varchar(255) DEFAULT NULL,
  `twitter` varchar(255) DEFAULT NULL,
  `web1` varchar(255) DEFAULT NULL,
  `web2` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `socialweb`
--

INSERT INTO `socialweb` (`social_id`, `company1`, `company2`, `facebook`, `instagram`, `tiktok`, `twitter`, `web1`, `web2`, `user_id`) VALUES
(1, 'company1', 'company2', 'facebook', 'instagram', 'tiktokk', 'twitter', 'web1', 'web2', 8);

-- --------------------------------------------------------

--
-- Table structure for table `url_product`
--

CREATE TABLE `url_product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `url_product`
--

INSERT INTO `url_product` (`id`, `name`, `url`, `type_id`, `product_id`, `user_id`) VALUES
(1, 'Student1273498', 'facebook.com/devillias', 1, 6, 8),
(2, 'Gmail', 'nero@gmail.com', 7, 6, 8),
(3, 'test30', '0123456789', 6, 6, 8),
(4, 'Twitter', 'twitter.com', 5, 6, 8),
(6, 'asus', 'telegram.com', 3, 6, 8),
(7, 'email2', 'nero2@gmail.com', 7, 6, 8),
(8, 'whatsapp1', 'whatsapp.com', 4, 6, 8),
(9, 'Vnexpress', 'vnexpress.net', 1, 6, 8),
(11, 'Twitter', 'twitter.com/nero3103', 5, 7, 9);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_ofbirth` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `link_image` varchar(255) DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  `name_image` varchar(255) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `address`, `date_ofbirth`, `description`, `email`, `enable`, `fullname`, `gender`, `lastname`, `link_image`, `locked`, `name_image`, `password`, `phone`, `province`, `username`) VALUES
(1, '1234', '2022-07-16 17:30:00', 'DEVERLOPER', 'John@gmail.com', b'1', 'Pham trong nghia', b'1', 'NGHIAPHAM', 'https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg', b'1', 'v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg', '$2a$10$z8KeA/fdeZoGsquGZZ2eQuFv18Wei8AFjto4N/si3StrO0RmqLGkC', 'Go Vap', 'TPHCM', 'John'),
(2, '1234', '2022-07-16 17:30:00', 'DEVERLOPER', 'will@gmail.com', b'0', 'Pham trong nghia', b'1', 'NGHIAPHAM', 'https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg', b'0', 'v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg', '$2a$10$.lHQOTOKEM1Q5Q5ssgMwBu6oATJG8ze7CgKgqcj8vrtRPqXj.wmve', 'Go Vap', 'TPHCM', 'Will'),
(3, '221B, Baker Street', '2000-03-31 07:00:00', 'awsdfs', 'lazada@gmail.com', b'1', 'lazada shoppee', b'0', 'shoppee', 'https://res.cloudinary.com/tphcm/image/upload/v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', b'1', 'v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', '$2a$10$MwARCq7s9Q8geFj3Ss3zUufauZbC0OJG1OIH8BtsrmTKAb2qfnVwq', '0123456789', 'Tay Ninh', 'lazada'),
(4, '221B, Baker Street', '2000-03-31 07:00:00', 'awsdfs', 'nero1@gmail.com', b'0', 'lazada shoppee', b'0', 'shoppee', 'https://res.cloudinary.com/tphcm/image/upload/v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', b'0', 'v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', '$2a$10$NpGPtFSSurCpwXFVUoo.SeLKDply.o6FwLnUsgjL2XiIxscLd2oRW', '0123456789', 'Tay Ninh', 'nero1'),
(5, '221B, Baker Street', '2000-03-31 07:00:00', 'awsdfs', 'nero2@gmail.com', b'0', 'lazada shoppee', b'0', 'shoppee', 'https://res.cloudinary.com/tphcm/image/upload/v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', b'0', 'v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', '$2a$10$BNh7Xl52o5rVVOtUoB37Du/vP987UD5gUVGSkf/75I0nEVI2I7S1a', '0123456789', 'Tay Ninh', 'nero2'),
(6, '221B, Baker Street', '2000-03-31 07:00:00', 'awsdfs', 'nero3@gmail.com', b'0', 'lazada shoppee', b'0', 'shoppee', 'https://res.cloudinary.com/tphcm/image/upload/v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', b'0', 'v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', '$2a$10$XZAWBmTuPjC2z6/XO2me9OCw9CYxPaO6SwJgbyaxRUh9NRTzkitQW', '0123456789', 'Tay Ninh', 'nero3'),
(7, '221B, Baker Street', '2000-03-31 07:00:00', 'awsdfs', 'nero5@gmail.com', b'0', 'lazada shoppee', b'0', 'shoppee', 'https://res.cloudinary.com/tphcm/image/upload/v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', b'0', 'v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', '$2a$10$T3IsySxstpdiUEw.fGpYluI4o3Emc73YzGUkggWjR.qDsLwO3bDqK', '0123456789', 'Tay Ninh', 'nero5'),
(8, '221B, Baker Street', '2000-03-31 07:00:00', 'Android Java Developer', 'nero@gmail.com', b'1', 'lazada shoppee', b'0', 'shoppee', 'https://res.cloudinary.com/tphcm/image/upload/v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', b'1', 'v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', '$2a$10$W5mr9aU9pkqERLxVzcNpEeYlbN2RvTFTxnPo1iAtSEgwfiAaaPmqe', '0399345342', 'Tay Ninh', 'nero'),
(9, '221B, Baker Street', '2000-03-31 07:00:00', 'awsdfs', 'saro@gmail.com', b'1', 'lazada shoppee', b'0', 'shoppee', 'https://res.cloudinary.com/tphcm/image/upload/v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', b'1', 'v1651378139/AvatarDefault/Anh-avatar-dep-chat-lam-hinh-dai-dien_bpyymd.jpg', '$2a$10$ONciI71aSaz2hBnOcV/cp.bD3JeIgAtn/s.tRsmq4YAerscX4E.da', '0123456789', 'Tay Ninh', 'saro');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `confirmation_token`
--
ALTER TABLE `confirmation_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbcnap2kh2odaogu0jwb6yhubt` (`app_user_id`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `link_type`
--
ALTER TABLE `link_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6161shkjqug4b2dsdu5g3emyl` (`category_id`),
  ADD KEY `FKa92736dcvkkjo7mfxw1fk1hio` (`process_id`),
  ADD KEY `FK787ibr3guwp6xobrpbofnv7le` (`product_id`),
  ADD KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`);

--
-- Indexes for table `order_process`
--
ALTER TABLE `order_process`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK47nyv78b35eaufr6aa96vep6n` (`user_id`);

--
-- Indexes for table `reset_password`
--
ALTER TABLE `reset_password`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa4ygygup89nl5utoaqjpp29bb` (`app_user_id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiyof1sindb9qiqr9o8npj8klt` (`product_id`),
  ADD KEY `FK6cpw2nlklblpvc7hyt7ko6v3e` (`user_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `socialweb`
--
ALTER TABLE `socialweb`
  ADD PRIMARY KEY (`social_id`),
  ADD UNIQUE KEY `UK1to0figb55wigx3ar7pup07qi` (`user_id`);

--
-- Indexes for table `url_product`
--
ALTER TABLE `url_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgrufi56syibw66by2nkn48mjl` (`type_id`),
  ADD KEY `FK8x0ipvbu7fdlrgee5fx0wdjbk` (`product_id`),
  ADD KEY `FK2hknhev63g9p3202oneq31dch` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `discount`
--
ALTER TABLE `discount`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `link_type`
--
ALTER TABLE `link_type`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `order_process`
--
ALTER TABLE `order_process`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `socialweb`
--
ALTER TABLE `socialweb`
  MODIFY `social_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `url_product`
--
ALTER TABLE `url_product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `confirmation_token`
--
ALTER TABLE `confirmation_token`
  ADD CONSTRAINT `FKbcnap2kh2odaogu0jwb6yhubt` FOREIGN KEY (`app_user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK6161shkjqug4b2dsdu5g3emyl` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FK787ibr3guwp6xobrpbofnv7le` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKa92736dcvkkjo7mfxw1fk1hio` FOREIGN KEY (`process_id`) REFERENCES `order_process` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK47nyv78b35eaufr6aa96vep6n` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `reset_password`
--
ALTER TABLE `reset_password`
  ADD CONSTRAINT `FKa4ygygup89nl5utoaqjpp29bb` FOREIGN KEY (`app_user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FK6cpw2nlklblpvc7hyt7ko6v3e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKiyof1sindb9qiqr9o8npj8klt` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `socialweb`
--
ALTER TABLE `socialweb`
  ADD CONSTRAINT `FK36y4ohsrka6lc56sfysptjbbk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `url_product`
--
ALTER TABLE `url_product`
  ADD CONSTRAINT `FK2hknhev63g9p3202oneq31dch` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK8x0ipvbu7fdlrgee5fx0wdjbk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKgrufi56syibw66by2nkn48mjl` FOREIGN KEY (`type_id`) REFERENCES `link_type` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
