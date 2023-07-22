# ZOHO CRM JAVA SDK 4.0 for API version 4

The Java SDK for Zoho CRM allows developers to easily create Java applications that can be integrated with Zoho CRM. This SDK serves as a wrapper for the REST APIs, making it easier to access and utilize the services of Zoho CRM. 
Authentication to access the CRM APIs is done through OAuth2.0, and the authentication process is streamlined through the use of the Java SDK. The grant and access/refresh tokens are generated and managed within the SDK code, eliminating the need for manual handling during data synchronization between Zoho CRM and the client application.

This repository includes the JAVA SDK for API v4 of Zoho CRM. Check [Versions](https://github.com/zoho/zohocrm-java-sdk-4.0/releases) for more details on the versions of SDK released for this API version.

License
=======

    Copyright (c) 2021, ZOHO CORPORATION PRIVATE LIMITED 
    All rights reserved. 

    Licensed under the Apache License, Version 2.0 (the "License"); 
    you may not use this file except in compliance with the License. 
    You may obtain a copy of the License at 
    
        http://www.apache.org/licenses/LICENSE-2.0 
    
    Unless required by applicable law or agreed to in writing, software 
    distributed under the License is distributed on an "AS IS" BASIS, 
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and 
    limitations under the License.

## Latest Version

- [4.0.0](/versions/4.0.0/README.md)

    - Records APIs Issue fixed.
        - Supported delete operation in multi-select lookup, multi-user lookup, image upload, and subform fields.

- [3.0.0](/versions/3.0.0/README.md)

    - Java SDK support TimeZone type.
    - Java SDK DBStore, FileStore, and OAuthToken class Enhancement.
    - Changed ShiftHour timezone field type.
    - Changed SendMail Attachments type.
    - Update Class constructor() method:
        - [DownloadAttachmentsOperations](https://github.com/zoho/zohocrm-java-sdk-4.0/commit/f1c11adebe78c84fd3a4638eea9ea688d05e798a#diff-619e50c388817911d30b89daf36281aba7254328d7178f77e466cb714800eb00)
        - [DownloadInlineImagesOperations](https://github.com/zoho/zohocrm-java-sdk-4.0/commit/f1c11adebe78c84fd3a4638eea9ea688d05e798a#diff-9c92c49c3d44570c8e1488567c4d9120bafa0ef034583f77af614567b7207a3d)
        - [PortalInviteOperations](https://github.com/zoho/zohocrm-java-sdk-4.0/commit/f1c11adebe78c84fd3a4638eea9ea688d05e798a#diff-f39717affe9d2a08435cf070f7d0fea072755c655f94df015a25fb59da9d8552)
        - [UserTypeUsersOperations](https://github.com/zoho/zohocrm-java-sdk-4.0/commit/f1c11adebe78c84fd3a4638eea9ea688d05e798a#diff-ef524ec4b6bb73c0ca5cc473435a8fde97dbaf2e6937df22c4d7fb01da868519)

- [2.0.0](/versions/2.0.0/README.md)
    
    - Users APIs Issue fixed.
        - Handled ***phone*** and ***mobile*** field datatype.

- [1.0.0](/versions/1.0.0/README.md)

    - Java SDK upgraded to support v4 APIs.

    - Structural changes to aid the process of SDK configuration and initialization user-friendly.

	  - Handled Token Persistence

	  - Updated UserSignature from Mandatory to Optional.

      - user_mail key in DBStore and FileStore is updated to user_name.

    - Java SDK improved to support the following new APIs

        - [AssociateEmail](https://www.zoho.com/crm/developer/docs/api/v4/associate-email.html)
        - [Backup](https://www.zoho.com/crm/developer/docs/api/v4/get-backup-info.html)
        - [BusinessHours](https://www.zoho.com/crm/developer/docs/api/v4/get-business-hours.html)
        - [CancelMeetings](https://www.zoho.com/crm/developer/docs/api/v4/meeting-cancel.html)
        - [DealContactRoles](https://www.zoho.com/crm/developer/docs/api/v4/get-contact-roles-of-a-specific-deal.html)
        - [DownloadEmailAttachmnets](https://www.zoho.com/crm/developer/docs/api/v4/download-email-attachments.html)
        - [DownloadInlineImagesofanEmail](https://www.zoho.com/crm/developer/docs/api/v4/download-inline-images.html)
        - [EmailSharing](https://www.zoho.com/crm/developer/docs/api/v4/get-email-shared-details.html)
        - [EmailRelatedrecords](https://www.zoho.com/crm/developer/docs/api/v4/get-email-rel-list.html)
        - [FieldMapDependency](https://www.zoho.com/crm/developer/docs/api/v4/get-map-dependency.html)
        - [fromAddresses](https://www.zoho.com/crm/developer/docs/api/v4/get-from-addresses-list.html)
        - [Holidays](https://www.zoho.com/crm/developer/docs/api/v4/get-holidays.html)
        - [MassChangeOwner](https://www.zoho.com/crm/developer/docs/api/v4/mass-change-owner.html)
        - [MassConvert](https://www.zoho.com/crm/developer/docs/api/v4/mass-convert-lead.html)
        - [MassDeleteCVID](https://www.zoho.com/crm/developer/docs/api/v4/mass-delete.html)
        - [Portals](https://www.zoho.com/crm/developer/docs/api/v4/get-portals.html)
        - [PortalInvite](https://www.zoho.com/crm/developer/docs/api/v4/invite-user.html)
        - [ShiftHours](https://www.zoho.com/crm/developer/docs/api/v4/get-shift-hours.html)
        - [UserGroups](https://www.zoho.com/crm/developer/docs/api/v4/get-user-groups.html)
        - [UserTerritories](https://www.zoho.com/crm/developer/docs/api/v4/get-user-territories.html)
        - [UserType](https://www.zoho.com/crm/developer/docs/api/v4/get-user-types.html)
        - [UserTypeUsers](https://www.zoho.com/crm/developer/docs/api/v4/get-users-user-type.html)
	

- [1.0.0-beta](/versions/1.0.0-beta/README.md)

    - Beta version of CRM v4 APIs.

For older versions, please [refer](https://github.com/zoho/zohocrm-java-sdk-4.0/releases).


## Including the SDK in your project
You can include the SDK to your project using:
- Maven
- [Gradle](/versions/3.0.0/README.md)
- [Download SDK jar](https://maven.zohodl.com/com/zoho/crm/zohocrmsdk-4-0/4.0.0/zohocrmsdk-4-0-4.0.0.jar)

For including the latest [version](https://github.com/zoho/zohocrm-java-sdk-4.0/releases/tag/4.0.0) using Maven, include the following in your **pom.xml** file, which will get created once your **Java** project is created using Maven.

    ```xml
    <repositories>
        <repository>
            <id>zohocrmsdk-4-0</id>
            <url>https://maven.zohodl.com</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>com.zoho.crm</groupId>
            <artifactId>zohocrmsdk-4-0</artifactId>
            <version>4.0.0</version>
        </dependency>
    </dependencies>
    ```

For more details, kindly refer [here](/versions/4.0.0/README.md).
