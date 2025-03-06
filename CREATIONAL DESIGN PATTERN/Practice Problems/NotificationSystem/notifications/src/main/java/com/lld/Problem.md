<!-- Notification Service
Problem: Implement a notification system where users can receive messages via Email, SMS, or Push Notification. Use the Factory Pattern to create instances of different notification services.

Constraints:

Each notification type should implement a sendNotification(message, recipient) method.
The factory should take an input (e.g., "EMAIL", "SMS", "PUSH") and return the corresponding notification service. -->

## Key Requirements

1. Different Notification Types:

   Email Notification
   SMS Notification
   Push Notification

2. Common Interface:

   All notifications should implement a common method

3. Factory Implementation:

   The factory should take an input (e.g., "EMAIL", "SMS", "PUSH") and return the corresponding notification service.

4. Extensibility:

   The system should allow adding new notification types without modifying existing code (Open-Closed Principle).
