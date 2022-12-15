package site.book.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.book.project.domain.Book;
import site.book.project.repository.SearchRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class SearchService {

    private final SearchRepository searchRepository;
    
    @Transactional(readOnly = true)
    public List<Book> search(String type, String keyword) {
        List<Book> list = null;

        // 드랍 다운 검색 타입(value 속성):
        if(type.equals("all")) { // 통합 검색
            return list = searchRepository.unifiedSearchByKeyword(keyword);
        }  else if (type.equals("do")) { // 국내 도서 검색
            return list = searchRepository.domesticSearchByKeyword(keyword);
        } else if (type.equals("fo")) { // 외국 도서 검색
            return list = searchRepository.foreignSearchByKeyword(keyword);
        } else if (type.equals("au")) { // 저자 검색
            return list = searchRepository.authorSearchByKeyword(keyword);
        }
        return list;
    }
    
    @Transactional(readOnly = true)
    public List<Book> research(String type, String keyword, String order){
        List<Book> list = null;
         
        if (order.equals("highPrice")) { // 최고가순 정렬
            if(type.equals("all")) { // 통합 검색
                return list = searchRepository.researchOrderAllByHighPrice(keyword);
            }  else if (type.equals("do")) { // 국내 도서 검색
                String orderType = "국내도서";
                return list = searchRepository.researchOrderByHighPrice(keyword, orderType);
            } else if (type.equals("fo")) { // 외국 도서 검색
                String orderType = "외국도서";
                return list = searchRepository.researchOrderByHighPrice(keyword, orderType);
            } else if (type.equals("au")) { // 저자 검색
                String orderType = "저자";
                return list = searchRepository.researchOrderByHighPrice(keyword, orderType);
            }
        } else if (order.equals("lowPrice")) { // 최저가순 정렬
            if(type.equals("all")) { // 통합 검색
                return list = searchRepository.researchOrderAllByLowPrice(keyword);
            }  else if (type.equals("do")) { // 국내 도서 검색
                String orderType = "국내도서";
                return list = searchRepository.researchOrderByLowPrice(keyword, orderType);
            } else if (type.equals("fo")) { // 외국 도서 검색
                String orderType = "외국도서";
                return list = searchRepository.researchOrderByLowPrice(keyword, orderType);
            } else if (type.equals("au")) { // 저자 검색
                String orderType = "저자";
                return list = searchRepository.researchOrderByLowPrice(keyword, orderType);
            }
        } else if (order.equals("publishedDate")) {
            if(type.equals("all")) { // 통합 검색
                return list = searchRepository.researchOrderAllByPublishedDate(keyword);
            }  else if (type.equals("do")) { // 국내 도서 검색
                String orderType = "국내도서";
                return list = searchRepository.researchOrderByPublishedDate(keyword, orderType);
            } else if (type.equals("fo")) { // 외국 도서 검색
                String orderType = "외국도서";
                return list = searchRepository.researchOrderByPublishedDate(keyword, orderType);
            } else if (type.equals("au")) { // 저자 검색
                String orderType = "저자";
                return list = searchRepository.researchOrderByPublishedDate(keyword, orderType);
            }
        } else if (order.equals("reviewCount") || order.equals("hitCount")) {
            if(type.equals("all")) { // 통합 검색
                return list = searchRepository.unifiedSearchByKeyword(keyword);
            }  else if (type.equals("do")) { // 국내 도서 검색
                return list = searchRepository.domesticSearchByKeyword(keyword);
            } else if (type.equals("fo")) { // 외국 도서 검색
                return list = searchRepository.foreignSearchByKeyword(keyword);
            } else if (type.equals("au")) { // 저자 검색
                return list = searchRepository.authorSearchByKeyword(keyword);
            }
        }

        return list;
    }
    
}
