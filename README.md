# ec2-tools

A Clojure library designed to help in admin your ec2 account and only those.
Extract data, create nodes or else. This part is up to you.

## Setup

Create a file *~/.ec2-tools/config.clj* which contains your ec2 credentials:

```clj
(def aws-ec2-credentials {:aws-access-key-id     "your-public-access-key
                          :aws-secret-access-key "your-secret-access-key))
```

That's it.

## Usage

Now connect to your repl and compile the namespace ec2_api.

### Examples

For example, if you want to list the regions, you can:

```sh
(ls-regions)
```

The result is under a map such as this...

```clj
{:trace-redirects
 ["https://ec2.amazonaws.com?AWSAccessKeyId=your-access-key&Action=DescribeRegions&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=2012-10-02T17%3A35%3A43Z&Version=2012-08-15&Signature=your-request-hashed-and-signed-and-url-encoded],
 :status 200,
 :headers
 {"content-type" "text/xml;charset=UTF-8",
  "transfer-encoding" "chunked",
  "content-encoding" "gzip",
  "vary" "Accept-Encoding",
  "date" "Tue, 02 Oct 2012 17:35:43 GMT",
  "server" "AmazonEC2"},
 :body
 "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DescribeRegionsResponse xmlns=\"http://ec2.amazonaws.com/doc/2012-08-15/\">\n    <requestId>998b4807-565c-4a37-8928-531bc0fa18bd</requestId>\n    <regionInfo>\n        <item>\n            <regionName>eu-west-1</regionName>\n            <regionEndpoint>ec2.eu-west-1.amazonaws.com</regionEndpoint>\n        </item>\n        <item>\n            <regionName>sa-east-1</regionName>\n            <regionEndpoint>ec2.sa-east-1.amazonaws.com</regionEndpoint>\n        </item>\n
  <item>\n            <regionName>us-east-1</regionName>\n            <regionEndpoint>ec2.us-east-1.amazonaws.com</regionEndpoint>\n        </item>\n        <item>\n            <regionName>ap-northeast-1</regionName>\n            <regionEndpoint>ec2.ap-northeast-1.amazonaws.com</regionEndpoint>\n        </item>\n        <item>\n            <regionName>us-west-2</regionName>\n            <regionEndpoint>ec2.us-west-2.amazonaws.com</regionEndpoint>\n        </item>\n        <item>\n            <regionName>us-west-1</regionName>\n            <regionEndpoint>ec2.us-west-1.amazonaws.com</regionEndpoint>\n        </item>\n        <item>\n            <regionName>ap-southeast-1</regionName>\n            <regionEndpoint>ec2.ap-southeast-1.amazonaws.com</regionEndpoint>\n        </item>\n    </regionInfo>\n</DescribeRegionsResponse>"}
nil
```

### More primitive examples

If you want some missing functions, you have the *amazon-query* primary function:

```clj
(amazon-query :get {"Action" "DescribeRegions"})
```

This is the same query as before so the result is the same too.
You can add the wanted other params as a new entry in the map.

```clj
(amazon-query :get {"Action"       "DescribeRegions"
                    "RegionName.1" "us-east-1"})
```

The sky is the limit here.

## License

Copyright © 2012 commiters

Distributed under the Eclipse Public License, the same as Clojure.
