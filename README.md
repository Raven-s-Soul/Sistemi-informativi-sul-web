# SiwBooks

This repository contains the source code for **SiwBooks**, a web application developed as part of a university coursework project for the Web Information Systems course at Roma Tre University.

## Project Overview

**SiwBooks** is a web-based information system designed to provide comprehensive access to a catalog of books and their authors, while supporting the creation and moderation of user-submitted reviews. The platform caters to three distinct user roles—occasional visitors, registered users, and administrators—each with tailored permissions to ensure data integrity and a seamless user experience.

### User Roles and Functionality

- **Occasional users** (non-authenticated visitors) can:
  - Browse the full catalog of books and authors.
  - Access detailed information, including reviews and author biographies.

- **Registered users** (authenticated):
  - Retain all browsing capabilities.
  - Submit a single review per book, comprising a rating and commentary.
  - Manage their own reviews through a personal dashboard.

- **Administrators**:
  - Administer the catalog by adding, editing, or deleting books and authors.
  - Moderate user reviews by removing inappropriate content.
  - Manage images associated with books and authors.

The system implements secure authentication, role-based access control, and a carefully designed domain model that maintains data consistency across all operations.
