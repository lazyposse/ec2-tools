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

For example, if you want to list the regions you got:

```sh
(amazon-query :get "Action=DescribeRegions")
```

The result is under a map such as this...

## License

Copyright © 2012 commiters

Distributed under the Eclipse Public License, the same as Clojure.
