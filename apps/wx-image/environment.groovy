def domain = "http://static.ihoubo.com"

environments {
    dev {
        file {
            root = '/static/'
            pathfix = 'upload'
            urlpath = domain
        }
    }

    test {
        file {
            root = '/static/'
            pathfix = 'upload'
            urlpath = domain
        }
    }

    prod {
        file {
            root = '/static/'
            pathfix = 'upload'
            urlpath = domain
        }
    }
}