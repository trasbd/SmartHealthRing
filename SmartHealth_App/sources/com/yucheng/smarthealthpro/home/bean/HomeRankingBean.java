package com.yucheng.smarthealthpro.home.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class HomeRankingBean {
    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private MyRankInfoBean myRankInfo;
        private OtherRankInfoBean otherRankInfo;

        public MyRankInfoBean getMyRankInfo() {
            return this.myRankInfo;
        }

        public void setMyRankInfo(MyRankInfoBean myRankInfo) {
            this.myRankInfo = myRankInfo;
        }

        public OtherRankInfoBean getOtherRankInfo() {
            return this.otherRankInfo;
        }

        public void setOtherRankInfo(OtherRankInfoBean otherRankInfo) {
            this.otherRankInfo = otherRankInfo;
        }

        public static class MyRankInfoBean {
            private String likedCount;
            private String ranking;
            private String rankingList;
            private String userId;

            public String getLikedCount() {
                return this.likedCount;
            }

            public void setLikedCount(String likedCount) {
                this.likedCount = likedCount;
            }

            public String getRanking() {
                return this.ranking;
            }

            public void setRanking(String ranking) {
                this.ranking = ranking;
            }

            public String getRankingList() {
                return this.rankingList;
            }

            public void setRankingList(String rankingList) {
                this.rankingList = rankingList;
            }

            public String getUserId() {
                return this.userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }

        public static class OtherRankInfoBean {
            private int currentPage;
            private int isMore;
            private List<ItemsBean> items;
            private int pageSize;
            private int startIndex;
            private int totalNum;
            private int totalPage;

            public int getCurrentPage() {
                return this.currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getIsMore() {
                return this.isMore;
            }

            public void setIsMore(int isMore) {
                this.isMore = isMore;
            }

            public int getPageSize() {
                return this.pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getStartIndex() {
                return this.startIndex;
            }

            public void setStartIndex(int startIndex) {
                this.startIndex = startIndex;
            }

            public int getTotalNum() {
                return this.totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public int getTotalPage() {
                return this.totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public List<ItemsBean> getItems() {
                return this.items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                private String headImg;
                private int isLike;
                private String likedCount;
                private String nickName;
                private String rankingList;
                private String userId;

                public String getHeadImg() {
                    return this.headImg;
                }

                public void setHeadImg(String headImg) {
                    this.headImg = headImg;
                }

                public int getIsLike() {
                    return this.isLike;
                }

                public void setIsLike(int isLike) {
                    this.isLike = isLike;
                }

                public String getLikedCount() {
                    return this.likedCount;
                }

                public void setLikedCount(String likedCount) {
                    this.likedCount = likedCount;
                }

                public String getNickName() {
                    return this.nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getRankingList() {
                    return this.rankingList;
                }

                public void setRankingList(String rankingList) {
                    this.rankingList = rankingList;
                }

                public String getUserId() {
                    return this.userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }
            }
        }
    }
}
